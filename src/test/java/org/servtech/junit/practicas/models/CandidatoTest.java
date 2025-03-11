package org.servtech.junit.practicas.models;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.servtech.junitapp.ejemplos.models.Cuenta;
import org.servtech.junitapp.practicas.models.Candidato;
import org.servtech.junitapp.practicas.models.Partido;
import org.servtech.junitapp.practicas.models.Urna;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Probando la clase candidato")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class CandidatoTest {

    private Urna urna;
    private Candidato candidato1;
    private Candidato candidato2;

    @BeforeEach
    void initMetodoTest(){

        this.urna    = new Urna();
        this.candidato1 = new Candidato("John Doe", new Partido("Azul"));
        this.candidato2 = new Candidato("Angel de la Cueva", new Partido("Verde"));

        System.out.println("Iniciando el método");
    }

    @AfterEach
    void tearDownMetodoTest(){
        System.out.println("Finalizando el método de prueba");
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("Iniciando el test de candidato");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("Finalizando el test de candidato");
    }

    @Test
    @DisplayName("Probando el nombre del candidato...")
    void testNombreCuenta() {
        String esperado = "John Doe";
        String real = candidato1.getPersona();
        assertNotNull(real, ()-> "El candidato no puede ser nulo");
        assertEquals(esperado,real, ()->"El nombre del candidato (" + esperado + ") no corresponde al ingresado (" + real+")");
        assertTrue(real.equals("John Doe"),()->"El nombre del candidato esperado debe ser igual al real");
    }
}
