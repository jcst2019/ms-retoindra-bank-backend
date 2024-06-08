
# ms-retoindra-bank-backend

Este repositorio contiene el microservicio `ms-retoindra-bank-backend`, el cual es parte del sistema de gestión de clientes para el reto de Indra. El microservicio proporciona funcionalidades para administrar productos financieros de los clientes y sus datos asociados.

## Ramas

- `master`: Contiene la versión estable y desplegable del microservicio.
- `2024-001`: Desarrollo de Requerimientos.

## Instalación

1. Clona el repositorio:

   ```bash
    git clone https://github.com/jcst2019/ms-retoindra-bank-backend.git
    git clone https://github.com/jcst2019/starter-utilitarian-lib.git (Libreria)
## Uso
El microservicio proporciona diferentes puntos finales (endpoints) REST para interactuar con los clientes del banco y sus productos financieros.

A continuación, se muestran algunos ejemplos de las uris:

- GET /api/bank/productFindByUniqueCode?uniqueCode={uniqueCode}: Encuentra un productos financiero por su código único.
- GET /api/bank/customerFindByUniqueCode?uniqueCode={uniqueCode}: Encuentra un cliente por su código único.
- GET /api/bank/informationFindByUniqueCode?uniqueCode={uniqueCode}: Encuentra un cliente con sus respectivos productos financiero por su código único

### Desarrollado por:

**Juan Carlos Solar Torres**

📱 **Celular:** +51 951800369

📧 **Correo:** juansolar4@gmail.com

