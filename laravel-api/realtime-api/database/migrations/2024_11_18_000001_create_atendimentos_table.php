<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    public function up(): void {
        if (!Schema::hasTable('atendimentos')) {
        Schema::create('atendimentos', function (Blueprint $table) {
            $table->id();
            $table->foreignId('carro_id')->constrained('carros')->onDelete('cascade');
            $table->foreignId('cliente_id')->constrained('clientes')->onDelete('cascade');
            $table->date('data_agendamento');
            $table->time('horario_agendamento');
            $table->enum('tipo', ['REPARO', 'MANUTENCAO_PREVENTIVA', 'LIMPEZA']);
            $table->enum('status', ['AGENDADO', 'EM_ANDAMENTO', 'CONCLUIDO']);
            $table->timestamps();
        });
    }
}
    public function down(): void {
        Schema::dropIfExists('atendimentos');
    }
};
