<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Atendimento extends Model {
    use HasFactory;

    protected $fillable = [
        'carro_id',
        'cliente_id',
        'data_agendamento',
        'horario_agendamento',
        'tipo',
        'status',
    ];

    public function carro() {
        return $this->belongsTo(Carro::class);
    }

    public function cliente() {
        return $this->belongsTo(Cliente::class);
    }
}
