use Illuminate\Support\Facades\Route;
use AuthController;
use CarController;
use ClientController;
use AppointmentController;
use AtendimentoController;
// Rota de teste inicial
Route::get('/', function () {
    return response()->json(['message' => 'API Laravel está funcionando!']);
});

// Rotas de autenticação
Route::post('/auth/login', [AuthController::class, 'login']);
Route::post('/auth/register', [AuthController::class, 'register']);

// Rotas para Carros
Route::get('/cars', [CarController::class, 'index']); // Listar todos os carros
Route::post('/cars', [CarController::class, 'store']); // Criar um carro
Route::get('/cars/{id}', [CarController::class, 'show']); // Mostrar detalhes de um carro
Route::put('/cars/{id}', [CarController::class, 'update']); // Atualizar informações de um carro
Route::delete('/cars/{id}', [CarController::class, 'destroy']); // Deletar um carro

// Rotas para Clientes
Route::get('/clients', [ClientController::class, 'index']); // Listar todos os clientes
Route::post('/clients', [ClientController::class, 'store']); // Criar um cliente
Route::get('/clients/{id}', [ClientController::class, 'show']); // Mostrar detalhes de um cliente
Route::put('/clients/{id}', [ClientController::class, 'update']); // Atualizar informações de um cliente
Route::delete('/clients/{id}', [ClientController::class, 'destroy']); // Deletar um cliente

// Rotas para Atendimentos
Route::get('/appointments', [AppointmentController::class, 'index']); // Listar todos os atendimentos
Route::post('/appointments', [AppointmentController::class, 'store']); // Criar um atendimento
Route::get('/appointments/{id}', [AppointmentController::class, 'show']); // Mostrar detalhes de um atendimento
Route::put('/appointments/{id}', [AppointmentController::class, 'update']); // Atualizar informações de um atendimento
Route::delete('/appointments/{id}', [AppointmentController::class, 'destroy']); // Deletar um atendimento




Route::get('/events', [EventController::class, 'getUnreadEvents']);
Route::post('/events/acknowledge', [EventController::class, 'markEventAsRead']);



Route::fallback(function () {
    return response()->json(['error' => 'Rota não encontrada.'], 404);
});

