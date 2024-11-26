#!/usr/bin/env bash
# Use this script to test if a given TCP host/port are available

WAITFORIT_cmdname=${0##*/}

echoerr() { if [[ $WAITFORIT_QUIET -ne 1 ]]; then echo "$@" 1>&2; fi }

usage() {
    cat << USAGE >&2
Usage:
    $WAITFORIT_cmdname host:port [-s] [-t timeout] [-- command args]
    -h HOST | --host=HOST       Host or IP under test
    -p PORT | --port=PORT       TCP port under test
                                Alternatively, you specify the host and port as host:port
    -s | --strict               Only execute subcommand if the test succeeds
    -q | --quiet                Don't output any status messages
    -t TIMEOUT | --timeout=TIMEOUT
                                Timeout in seconds, zero for no timeout
    -- COMMAND ARGS             Execute command with args after the test finishes
USAGE
    exit 1
}

wait_for() {
    if [[ $WAITFORIT_TIMEOUT -gt 0 ]]; then
        echoerr "$WAITFORIT_cmdname: waiting $WAITFORIT_TIMEOUT seconds for $WAITFORIT_HOST:$WAITFORIT_PORT"
    else
        echoerr "$WAITFORIT_cmdname: waiting for $WAITFORIT_HOST:$WAITFORIT_PORT without a timeout"
    fi
    WAITFORIT_start_ts=$(date +%s)
    while :
    do
        if [[ $WAITFORIT_ISBUSY -eq 1 ]]; then
            nc -z $WAITFORIT_HOST $WAITFORIT_PORT
            WAITFORIT_result=$?
        else
            (echo > /dev/tcp/$WAITFORIT_HOST/$WAITFORIT_PORT) >/dev/null 2>&1
            WAITFORIT_result=$?
        fi
        if [[ $WAITFORIT_result -eq 0 ]]; then
            WAITFORIT_end_ts=$(date +%s)
            echoerr "$WAITFORIT_cmdname: $WAITFORIT_HOST:$WAITFORIT_PORT is available after $((WAITFORIT_end_ts - WAITFORIT_start_ts)) seconds"
            break
        fi
        sleep 1
        if [[ $WAITFORIT_TIMEOUT -gt 0 ]]; then
            WAITFORIT_curr_ts=$(date +%s)
            if [[ $((WAITFORIT_curr_ts - WAITFORIT_start_ts)) -ge $WAITFORIT_TIMEOUT ]]; then
                echoerr "$WAITFORIT_cmdname: timeout occurred after waiting $WAITFORIT_TIMEOUT seconds for $WAITFORIT_HOST:$WAITFORIT_PORT"
                break
            fi
        fi
    done
    return $WAITFORIT_result
}

wait_for_wrapper() {
    # In order to support SIGINT during timeout: http://unix.stackexchange.com/a/57692
    if [[ $WAITFORIT_QUIET -eq 1 ]]; then
        timeout $WAITFORIT_BUSYTIMEOUT wait_for
        WAITFORIT_RESULT=$?
    else
        timeout $WAITFORIT_BUSYTIMEOUT wait_for
        WAITFORIT_RESULT=$?
    fi
    if [[ $WAITFORIT_RESULT -ne 0 ]]; then
        exit $WAITFORIT_RESULT
    fi
}

WAITFORIT_HOST=""
WAITFORIT_PORT=""
WAITFORIT_TIMEOUT=15
WAITFORIT_STRICT=0
WAITFORIT_QUIET=0
WAITFORIT_ISBUSY=0
WAITFORIT_CMD=""
for i in "$@"
do
case $i in
    -h=*|--host=*)
    WAITFORIT_HOST="${i#*=}"
    shift # past argument=value
    ;;
    -p=*|--port=*)
    WAITFORIT_PORT="${i#*=}"
    shift # past argument=value
    ;;
    -t=*|--timeout=*)
    WAITFORIT_TIMEOUT="${i#*=}"
    shift # past argument=value
    ;;
    -s|--strict)
    WAITFORIT_STRICT=1
    shift # past argument with no value
    ;;
    -q|--quiet)
    WAITFORIT_QUIET=1
    shift # past argument with no value
    ;;
    --)
    shift
    WAITFORIT_CMD="$@"
    break
    ;;
    *)
        echoerr "Unknown argument: $i"
        usage
    ;;
esac
done

if [[ "$WAITFORIT_HOST" == "" || "$WAITFORIT_PORT" == "" ]]; then
    echoerr "Error: you need to provide a host and port to test."
    usage
fi

WAITFORIT_busy() {
    if nc -z $WAITFORIT_HOST $WAITFORIT_PORT; then
        return 0
    else
        return 1
    fi
}

WAITFORIT_wrapper() {
    if WAITFORIT_busy; then
        return 0
    else
        return 1
    fi
}

wait_for_wrapper
if [[ $? -ne 0 ]]; then
    exit $?
fi

if [[ $WAITFORIT_CMD != "" ]]; then
    exec $WAITFORIT_CMD
else
    exit 0
fi
