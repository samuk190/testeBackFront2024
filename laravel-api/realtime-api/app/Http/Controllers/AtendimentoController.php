<?php

namespace App\Http\Controllers;

use App\Models\Atendimento;
use Illuminate\Http\Request;

class AtendimentoController extends Controller
{
    public function index(Request $request)
    {
        $status = $request->query('status', null);
        $query = Atendimento::query();

        if ($status) {
            $query->where('status', $status);
        }

        $atendimentos = $query->paginate(10);
        return response()->json($atendimentos);
    }

    public function store(Request $request)
    {
        $validated = $request->validate([
            'cliente_id' => 'required|exists:clientes,id',
            'veiculo_id' => 'required|exists:carros,id',
            'tipo' => 'required|string',
            'status' => 'required|string',
            'data_agendamento' => 'required|date',
            'horario_agendamento' => 'required|date_format:H:i',
        ]);

        $atendimento = Atendimento::create($validated);
        event(new \App\Events\AppointmentCreated($atendimento));

        return response()->json($atendimento, 201);
    }
}
