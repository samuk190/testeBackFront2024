<?php

namespace App\Listeners;

use App\Events\AppointmentCreated;
use Illuminate\Support\Facades\Log;

class NotifyFrontend
{
    public function handle(AppointmentCreated $event)
    {
        // Simulação de notificação em tempo real
        Log::info('Notificação de atendimento: ' . $event->atendimento->id);
    }
}
