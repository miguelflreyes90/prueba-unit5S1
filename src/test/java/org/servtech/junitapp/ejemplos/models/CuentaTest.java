package org.servtech.junitapp.ejemplos.models;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.servtech.junitapp.ejemplos.exception.DineroInsuficienteException;

import java.math.BigDecimal;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Probando la Clase Cuenta")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CuentaTest {
    Cuenta cuenta;

    @BeforeEach
    void initMetodoTest(){
        this.cuenta = new Cuenta("Antonio", new BigDecimal("1000.12345"));
        System.out.println("Iniciando el método");
    }

    @AfterEach
    void tearDownMetodoTest(){
        System.out.println("Finalizando el método de prueba");
    }

    @BeforeAll
   static void beforeAll(){
        System.out.println("Iniciando el test de Cuenta");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("Finalizando el test de Cuenta");
    }

    @Test
    @DisplayName("Probando el nombre de la cuenta corriente...")
    void testNombreCuenta() {
        //cuenta = new Cuenta("Antonio", new BigDecimal("1000.12345"));
        //cuenta.setPersona("Antonio");
        String esperado = "Antonio";
        String real = cuenta.getPersona();
        //Assertions.assertEquals(esperado, real);
        assertNotNull(real, ()-> "La cuenta no puede ser nulo");
        assertEquals(esperado,real, ()->"El nombre de la cuenta no es el que se esperaba " + esperado + " sin embargo fue " + real);
        assertTrue(real.equals("Antonio"),()->"Nombre cuenta esperado debe ser igual al real");
    }

    @Test
    @DisplayName("Probando el saldo de la cuenta...")
    void testSaldoCuenta() {
        //cuenta = new Cuenta("Antonio", new BigDecimal("1000.12345"));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    @DisplayName("Probando la referencia de la cuenta...")
    void testReferenciaCuenta() {
        Cuenta cuenta1 = new Cuenta("Juan Doe", new BigDecimal("8900.9997"));
        Cuenta cuenta2 = new Cuenta("Juan Doe", new BigDecimal("8900.9997"));
        //assertNotEquals(cuenta2,cuenta1);
        assertEquals(cuenta2, cuenta1);
    }

    @Test
    @DisplayName("Probando el método de débito cuenta...")
    void testDebitoCuenta() {
        //cuenta = new Cuenta("Antonio", new BigDecimal("1000.12345"));
        cuenta.debito(new BigDecimal("100"));
        assertNotNull(cuenta.getSaldo());
        assertEquals(900, cuenta.getSaldo().intValue()); //comprar por entero
        assertEquals("900.12345", cuenta.getSaldo().toPlainString()); //comparar por string
    }

    @Test
    @DisplayName("Probando el método de crédito cuenta...")
    void testCreditoCuenta() {
        //cuenta = new Cuenta("Antonio", new BigDecimal("1000.12345"));
        cuenta.credito(new BigDecimal("100"));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100, cuenta.getSaldo().intValue()); //comparar por entero
        assertEquals("1100.12345", cuenta.getSaldo().toPlainString()); //comparar por string
    }

    @Test
    @DisplayName("Probando el método dinero insuficiente...")
    void testDineroInsuficienteCuenta() {
        //cuenta = new Cuenta("Antonio", new BigDecimal("1000.12345"));
        Exception exception = assertThrows(DineroInsuficienteException.class, () -> {
            cuenta.debito(new BigDecimal(1500));
        });
        String actual = exception.getMessage();
        String esperado = "Dinero Insuficiente";
        assertEquals(esperado, actual);
    }

    @Test
    @DisplayName("Probando transferir dinero a otra cuenta...")
    void testTransferirDineroCuenta() {
        Cuenta cuenta1 = new Cuenta("Juan Doe", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Antonio", new BigDecimal("1500.8989"));

        Banco banco = new Banco();
        banco.setNombre("Banco del Estado");
        banco.transferir(cuenta2, cuenta1, new BigDecimal(500));
        assertEquals("1000.8989", cuenta2.getSaldo().toPlainString());
        assertEquals("3000", cuenta1.getSaldo().toPlainString());

    }

    @Test
    //@Disabled
    @DisplayName("Probando relación entre cuentas y bancos con assertAll...")
    void testRelacionBancoCuentas() {
        //fail("Se queda en pausa");
        Cuenta cuenta1 = new Cuenta("Juan Doe", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Antonio", new BigDecimal("1500.8989"));

        Banco banco = new Banco();
        banco.addCuenta(cuenta1);
        banco.addCuenta(cuenta2);
        banco.setNombre("Banco del Estado");

        banco.transferir(cuenta2, cuenta1, new BigDecimal(500));

        assertAll(
                () -> {
                    assertEquals("1000.8989", cuenta2.getSaldo().toPlainString(),()-> "El valor del saldo de la cuenta 2 no es el esperado");
                },
                () -> {
                    assertEquals("3000", cuenta1.getSaldo().toPlainString(), ()-> "El valor del saldo de la cuenta 1 no es el esperado");
                },
                () -> {
                    assertEquals(2, banco.getCuentas().size(),()-> "El banco no tiene las cuentas esperadas");
                },
                () -> {
                    assertEquals("Banco del Estado", cuenta1.getBanco().getNombre());
                },
                () -> {
                    assertEquals("Antonio", banco.getCuentas()
                            .stream()
                            .filter(c -> c.getPersona().equals("Antonio"))
                            .findFirst()
                            .get()
                            .getPersona());
                },
                () -> {
                    assertTrue(banco.getCuentas()
                            .stream()
                            .anyMatch(c -> c.getPersona()
                                    .equals("Antonio")));
                });
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testSoloWindows(){

    }
    @Test
    @EnabledOnOs({OS.LINUX, OS.MAC})
    void testSoloLinuxMac(){

    }

    @Test
    @DisabledOnOs({OS.WINDOWS})
    void testNoWindows(){

    }

    @Test
    @EnabledOnJre({JRE.JAVA_8})
    void testSoloJDK8(){

    }

    @Test
    @DisabledOnJre({JRE.JAVA_21})
    void testNoJDK21(){
    }

    @Test
    void imprimirSystemProperties(){
        Properties properties = System.getProperties();
        properties.forEach((k,v)-> System.out.println(k +":"+v));
    }

    @Test
    @EnabledIfSystemProperty(named = "java.version", matches="21.0.5")
    void testJavaVersion(){

    }

    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches=".*64.*")
    void testSolo64(){

    }
}