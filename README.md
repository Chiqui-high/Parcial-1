Parcial 1 - Estructura de Datos

Que es este proyecto

Este repositorio tiene las dos fases del parcial. La Fase 1 es un simulador simple de
raciones para refugiados, y la Fase 2 es una version mas completa de esa misma idea,
llamada Sistema de Asistencia Humanitaria y Refugiados, donde ya no hay un solo grupo
de personas sino una red de tres campamentos por los que los pacientes van pasando.

Fase 1

La Fase 1 esta en el archivo Main.java, en el paquete org.example. La idea es
sencilla: hay una fila de refugiados representados con numeros 0 o 1 segun su
preferencia de racion, y una pila de raciones tambien con 0 y 1. Cada refugiado que
esta al frente de la fila mira la racion que esta en el tope de la pila. Si coincide
con su preferencia, come y esa racion se saca de la pila. Si no coincide, el
refugiado se va al final de la fila a esperar otra oportunidad. Si en una vuelta
completa a la fila nadie logra comer, quiere decir que hay un bloqueo, porque nadie
quiere la racion que esta arriba, y ahi el programa se detiene y avisa que hubo
bloqueo.

Fase 2

Para la Fase 2 tocaba ampliar esa misma logica pero agregando mas cosas: ahora cada
persona es un paciente con cedula, una preferencia de racion, una receta medica y una
cantidad de intentos que va bajando cuando las cosas le salen mal. Y en vez de un
solo lugar, hay tres campamentos en fila, donde cada uno tiene su propia fila de
pacientes, su propia pila de raciones y su propio inventario de medicamentos.

Para esto se crearon dos clases nuevas ademas del main de esta fase.

La clase Paciente guarda los datos propios de cada persona: la cedula, la preferencia
de racion que puede ser 0 o 1, la receta medica que es un texto con letras como A, B
o C, los intentos que le quedan que empiezan en 3, y el estado en el que se
encuentra, que puede ser en espera, sanado o muerto. Ese estado se maneja con un enum
metido dentro de la misma clase Paciente, porque asi uno se asegura de que solo
existan esos tres valores posibles y no se pueda escribir mal en ninguna parte del
codigo. La cedula y la preferencia de racion no tienen forma de cambiarse despues de
crear el paciente, porque no tiene sentido que cambien a mitad de la simulacion. Lo
que si cambia es el estado y los intentos restantes, asi que esos dos si tienen su
metodo para modificarse. La clase tambien tiene su toString para poder imprimir un
paciente completo, y su equals y hashCode comparando todos los atributos, para que
dos pacientes se consideren iguales solo si coinciden en todo.

La clase Campamento guarda lo que le corresponde a cada campamento: un id para
identificarlo, la fila de pacientes que se maneja con una Queue, la pila de raciones
que se maneja con un Stack, y el inventario de medicamentos que se maneja con una
lista de caracteres, donde cada letra que hay disponible aparece repetida las veces
que haga falta, como si fuera una bolsa de fichas. Ahi tambien fue donde se metio la
logica de aprovisionar el campamento con raciones y medicamentos al azar, y la logica
de atender la fila durante un dia completo.

La forma en que se atiende la fila es la parte central del proyecto. Por cada
paciente que esta al frente se revisan dos cosas al mismo tiempo. La primera es si la
racion que esta en el tope de la pila coincide con lo que el paciente prefiere. La
segunda es si el inventario de medicamentos tiene, en la cantidad exacta que pide la
receta, todas las letras que el paciente necesita. Si las dos cosas se cumplen, el
paciente sana, se le descuenta la racion y los medicamentos que gasto, y sale de la
fila. Si cualquiera de las dos falla, el paciente se va al final de la fila a
intentar de nuevo mas adelante, por si en la siguiente vuelta ya hay otra racion
arriba o llegaron mas medicamentos.

Si se completa una vuelta entera por toda la fila sin que ni un solo paciente logre
sanar, eso es un bloqueo, igual que en la Fase 1. Cuando eso pasa, todos los que
quedaban en la fila pierden un intento de una vez y salen del campamento. Si el
campamento tiene un siguiente campamento en la red, esos pacientes se trasladan alla
para intentar suerte el proximo dia. Si el bloqueo ocurre en el Campamento 3, que es
el ultimo de la fila y no tiene a donde mas mandarlos, esos pacientes mueren ahi
mismo.

Toda esta decision de a donde va cada paciente trasladado, y quien muere y quien no,
se maneja desde el main, no desde la clase Campamento. La razon es que Campamento no
tiene forma de saber si es el primero, el segundo o el ultimo campamento de la red,
ni que existe algo despues de el. Por eso el metodo que atiende la fila solo devuelve
la lista de los pacientes que quedaron bloqueados, y es el main quien decide,
dependiendo de cual campamento sea, si los manda al siguiente o si los marca como
muertos. Por la misma razon, ni Paciente ni Campamento imprimen nada por consola,
solo hacen sus calculos y devuelven la informacion. Todo lo que se imprime en
pantalla, el estado y la accion de cada paciente en cada dia, el inventario inicial y
final de cada campamento y los resultados finales, se imprime desde el main de la
Fase 2.

El main de la Fase 2 se llama MainFase2 en vez de Main, porque el Main de la Fase 1
ya existia en el mismo paquete y Java no permite tener dos clases con el mismo nombre
en el mismo paquete. Entonces para no tener que mover la Fase 1 ni cambiarle nada, se
opto por dejar el Main original tal como estaba y ponerle a la clase principal de la
Fase 2 el nombre MainFase2.

Lo que hace este main es crear los tres campamentos, generar entre veinte y treinta
pacientes de forma aleatoria y meterlos en la fila del primer campamento, y despues
correr un ciclo por dias. En cada dia se revisa campamento por campamento, y si tiene
pacientes esperando, se le hace un aprovisionamiento aleatorio de raciones y
medicamentos y se le manda a atender su fila completa. Los pacientes que van quedando
trasladados de un campamento pasan a la fila del siguiente para el dia que sigue. El
ciclo termina solo cuando en un dia completo ningun campamento tuvo pacientes que
atender, es decir que ya todos sanaron o ya todos murieron.

Como esta organizado el repositorio

Dentro de src/main/java/org/example esta el Main de la Fase 1 tal como estaba antes.
Se creo una carpeta nueva llamada Model, tambien dentro de org.example, y ahi adentro
quedaron las clases Paciente y Campamento de la Fase 2. El archivo MainFase2.java se
dejo directamente en org.example, al mismo nivel que el Main de la Fase 1, ya que es
el punto de entrada de esta fase.

Para correr cualquiera de las dos fases desde IntelliJ basta con abrir el archivo
correspondiente, Main para la Fase 1 o MainFase2 para la Fase 2, y darle click
derecho sobre el codigo para correrlo, o usar el boton de play que aparece al lado
del metodo main.
