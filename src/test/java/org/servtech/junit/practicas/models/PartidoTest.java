package org.servtech.junit.practicas.models;

import org.junit.jupiter.api.*;
import org.servtech.junitapp.practicas.models.Candidato;
import org.servtech.junitapp.practicas.models.Partido;
import org.servtech.junitapp.practicas.models.Urna;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Probando la clase candidato")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PartidoTest {

    private Partido partido;
    String esperado;
    @BeforeEach
    void initMetodoTest(){

        this.partido = new Partido  ("Rojo");
        System.out.println("Iniciando el método");
    }

    @AfterEach
    void tearDownMetodoTest(){
        System.out.println("Finalizando el método de prueba");
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("Iniciando el test de partido politico");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("Finalizando el test de partido politico");
    }

    @Test
    @DisplayName("Probando el nombre del partido politico...")
    void testNombreCuenta() {
         esperado = "Rojo";
        String real = partido.getNombre();
        assertNotNull(real, ()-> "El partido político no puede ser nulo");
        assertEquals(esperado,real, ()->"El partido politico (" + esperado + ") no corresponde al ingresado (" + real+")");
        assertTrue(real.equals("Rojo"),()->"El partido politico esperado debe ser igual al real");
    }

}
