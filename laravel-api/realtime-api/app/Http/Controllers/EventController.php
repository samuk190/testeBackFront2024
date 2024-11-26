<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Event;

class EventController extends Controller
{
    public function getUnreadEvents()
    {
        // Retorna eventos que ainda não foram lidos
        $events = Event::where('is_read', false)->get();

        return response()->json($events);
    }

    public function markEventAsRead(Request $request)
    {
        $eventId = $request->input('id');

        // Marca o evento como lido
        $event = Event::findOrFail($eventId);
        $event->is_read = true;
        $event->save();

        return response()->json(['message' => 'Evento marcado como lido.']);
    }
}
