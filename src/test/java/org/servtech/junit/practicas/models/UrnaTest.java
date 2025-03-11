package org.servtech.junit.practicas.models;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.servtech.junitapp.ejemplos.models.Banco;
import org.servtech.junitapp.ejemplos.models.Cuenta;
import org.servtech.junitapp.practicas.models.Candidato;
import org.servtech.junitapp.practicas.models.Partido;
import org.servtech.junitapp.practicas.models.Urna;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Probando la urna...")
public class UrnaTest {

    private Urna urna;
    private  Candidato candidato1, candidato2;

    @BeforeAll
    static void beforeAll(){
        System.out.println("Iniciando el test de Cuenta");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("Finalizando el test de Cuenta");
    }

    @AfterEach
    void tearDownMetodoTest(){
        System.out.println("Finalizando el método de prueba");
    }

    @BeforeEach
    void setup(){
        this.urna = new Urna();
        this.candidato1 = new Candidato("John Doe", new Partido("Azul"));
        this.candidato2 = new Candidato("Angel de la Cueva", new Partido("Verde"));
        this.urna.addCandidato(candidato1);
        this.urna.addCandidato(candidato2);
    }

    @Test
    @DisplayName("Probando el partido politico")
    @Disabled
    void testPartidoPolico(){
            assertNotNull(candidato1, ()-> "El candidato  no puede ser nulo");
            assertEquals("Azul", candidato1.getPartido().getNombre(), ()-> "El partido del candidato no es el esperado");
    }

    @Test
    @DisplayName("Probando el partido politico solo en windows")
    @EnabledOnOs(OS.WINDOWS)
    void testPartidoPolicoSoloWindows(){
        assertNotNull(candidato1, ()-> "El candidato  no puede ser nulo");
        assertEquals("Azul", candidato1.getPartido().getNombre(), ()-> "El partido del candidato no es el esperado");
    }

    @Test
    @DisplayName("Probando la votación...")
    void testVotacion() {
        urna.votar(candidato1);
        urna.votar(candidato1);
        urna.votar(candidato2);


        assertAll(
                () -> {
                    assertEquals("2", candidato1.getVotos().toString(), ()-> "La cantidad de votos no es la esperada");
                },

                () -> {
                    assertEquals(2, urna.getCandidatos().size(),()-> "La urna no tiene los candidatos esperados");
                },
                () -> {
                    assertEquals("John Doe", urna.getCandidatos()
                            .stream()
                            .filter(c -> c.getPersona().equals("John Doe"))
                            .findFirst()
                            .get()
                            .getPersona());
                },
                () -> {
                    assertTrue(urna .getCandidatos()
                            .stream()
                            .anyMatch(c -> c.getPersona()
                                    .equals("John Doe")));
                });
    }


    @Test
    @DisplayName("Probando la votación solo windows...")
    @DisabledOnOs({OS.LINUX,OS.MAC})
    void testVotacionSoloWindows() {
        assertNotNull(candidato1, ()-> "El candidato 1 no puede ser nulo");
        urna.votar(candidato1);
        urna.votar(candidato1);

        assertNotNull(candidato2, ()-> "El candidato 2 no puede ser nulo");
        urna.votar(candidato2);


        assertAll(
                () -> {
                    assertEquals("2", candidato1.getVotos().toString(), ()-> "La cantidad de votos no es la esperada");
                },

                () -> {
                    assertEquals(2, urna.getCandidatos().size(),()-> "La urna no tiene los candidatos esperados");
                },
                () -> {
                    assertEquals("John Doe", urna.getCandidatos()
                            .stream()
                            .filter(c -> c.getPersona().equals("John Doe"))
                            .findFirst()
                            .get()
                            .getPersona());
                },
                () -> {
                    assertTrue(urna .getCandidatos()
                            .stream()
                            .anyMatch(c -> c.getPersona()
                                    .equals("John Doe")));
                });
    }

    @Test
    @DisplayName("Probando la votación solo en java 21...")
    @EnabledOnJre({JRE.JAVA_21})
    void testVotacionSoloJDK21() {
        urna.votar(candidato1);
        urna.votar(candidato1);
        urna.votar(candidato2);


        assertAll(
                () -> {
                    assertEquals("2", candidato1.getVotos().toString(), ()-> "La cantidad de votos no es la esperada");
                },

                () -> {
                    assertEquals(2, urna.getCandidatos().size(),()-> "La urna no tiene los candidatos esperados");
                },
                () -> {
                    assertEquals("John Doe", urna.getCandidatos()
                            .stream()
                            .filter(c -> c.getPersona().equals("John Doe"))
                            .findFirst()
                            .get()
                            .getPersona());
                },
                () -> {
                    assertTrue(urna .getCandidatos()
                            .stream()
                            .anyMatch(c -> c.getPersona()
                                    .equals("John Doe")));
                });
    }

    @Test
    @DisplayName("Probando la votación en 64bits...")
    @EnabledIfSystemProperty(named = "os.arch", matches=".*64.*")
    void testVotacionSolo64() {
        urna.votar(candidato1);
        urna.votar(candidato1);
        urna.votar(candidato2);


        assertAll(
                () -> {
                    assertEquals("2", candidato1.getVotos().toString(), ()-> "La cantidad de votos no es la esperada");
                },

                () -> {
                    assertEquals(2, urna.getCandidatos().size(),()-> "La urna no tiene los candidatos esperados");
                },
                () -> {
                    assertEquals("John Doe", urna.getCandidatos()
                            .stream()
                            .filter(c -> c.getPersona().equals("John Doe"))
                            .findFirst()
                            .get()
                            .getPersona());
                },
                () -> {
                    assertTrue(urna .getCandidatos()
                            .stream()
                            .anyMatch(c -> c.getPersona()
                                    .equals("John Doe")));
                });
    }

}
