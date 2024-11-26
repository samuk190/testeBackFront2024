<?php

namespace App\Events;

use App\Models\Atendimento;
use Illuminate\Foundation\Events\Dispatchable;
use Illuminate\Queue\SerializesModels;

class AppointmentCreated
{
    use Dispatchable, SerializesModels;

    public $atendimento;

    public function __construct(Atendimento $atendimento)
    {
        $this->atendimento = $atendimento;
    }
}
