package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Barrio;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Farmacia;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class FarmaciaTest extends SpringTest{

	
	@Test
    @Transactional @Rollback(true)
    public void pruebaConexion(){
        assertThat(getSession().isConnected()).isTrue();
    }
    
    @Test
    @Transactional @Rollback(true)
    @SuppressWarnings(value = { "unchecked" }) 
    public void testQueBuscaFarmaciasDeTurnoMartes() {
    	Farmacia farmacia1 = new Farmacia();
    	farmacia1.setDiaDeTurno("Martes");
    	farmacia1.setNombre("Farmacity");
    	getSession().save(farmacia1);
    	
    	Farmacia farmacia2 = new Farmacia();
    	farmacia2.setDiaDeTurno("Lunes");
    	farmacia2.setNombre("DrAhorro");
    	getSession().save(farmacia2);
    	
    	Farmacia farmacia3 = new Farmacia();
    	farmacia3.setDiaDeTurno("Jueves");
    	farmacia3.setNombre("Dofarma");
    	getSession().save(farmacia3);
    	
    	Farmacia farmacia4 = new Farmacia();
    	farmacia4.setDiaDeTurno("Martes");
    	farmacia4.setNombre("Central Oeste");
    	getSession().save(farmacia4);
    	
    	Session session1 = getSession();
    	List<Farmacia> listaTotales = session1.createCriteria(Farmacia.class).list();
    	List<Farmacia> listaMartes =
    			session1.createCriteria(Farmacia.class)
    			.add(Restrictions.eq("diaDeTurno", "Martes"))
    			.list();
    	
    	assertEquals(4,listaTotales.size());
    	assertEquals(2, listaMartes.size());
    	
    	
    }
    
    @Test
    @Transactional @Rollback(true)
    @SuppressWarnings(value = { "unchecked" }) 
    public void testQueBuscaFarmaciasDeUnaCalle() {
    	
    	Direccion direccion1 = new Direccion();
    	direccion1.setCalle("Florencio Varela");
    	getSession().save(direccion1);
    	
    	Direccion direccion2 = new Direccion();
    	direccion2.setCalle("Corrientes");
    	getSession().save(direccion2);
    	
    	Farmacia farmacia1 = new Farmacia();
    	farmacia1.setDiaDeTurno("Martes");
    	farmacia1.setNombre("Farmacity");
    	farmacia1.setDireccion(direccion2);
    	getSession().save(farmacia1);
    	
    	Farmacia farmacia2 = new Farmacia();
    	farmacia2.setDiaDeTurno("Lunes");
    	farmacia2.setNombre("DrAhorro");
    	farmacia2.setDireccion(direccion1);
    	getSession().save(farmacia2);
    	
    	Farmacia farmacia3 = new Farmacia();
    	farmacia3.setDiaDeTurno("Jueves");
    	farmacia3.setNombre("Dofarma");
    	farmacia3.setDireccion(direccion1);
    	getSession().save(farmacia3);
    	
    	Farmacia farmacia4 = new Farmacia();
    	farmacia4.setDiaDeTurno("Martes");
    	farmacia4.setNombre("Central Oeste");
    	farmacia4.setDireccion(direccion1);
    	getSession().save(farmacia4);
    	
  	  	Session session1 = getSession();
  	  	List<Farmacia> listaTotales = session1.createCriteria(Farmacia.class).list();
  	  	List<Farmacia> listaCalle =
    			session1.createCriteria(Farmacia.class)
    			.createAlias("direccion", "buscaCalle")
    			.add(Restrictions.eq("buscaCalle.calle", "Corrientes"))
    			.list();
  	  	
  	  	assertEquals(4, listaTotales.size());
    	assertEquals(1, listaCalle.size());
    }
    
    @Test
    @Transactional @Rollback(true)
    @SuppressWarnings(value={"unchecked"})
    public void testQueBuscaFarmaciasDeUnBarrio(){
    	Barrio floresta = new Barrio();
    	floresta.setNombre("Floresta");
    	getSession().save(floresta);
    	
    	Barrio almagro = new Barrio();
    	almagro.setNombre("Almagro");
    	getSession().save(almagro);
    	
    	Barrio caballito = new Barrio();
    	caballito.setNombre("Caballito");
    	getSession().save(caballito);
    	
    	
    	Direccion direccion1 = new Direccion();
    	direccion1.setCalle("Olivera");
    	direccion1.setBarrio(floresta);
    	getSession().save(direccion1);
    	
    	Direccion direccion2 = new Direccion();
    	direccion2.setCalle("Corrientes");
    	direccion2.setBarrio(almagro);
    	getSession().save(direccion2);
    	
    	Direccion direccion3 = new Direccion();
    	direccion3.setCalle("Medrano");
    	direccion3.setBarrio(almagro);
    	getSession().save(direccion3);
    	
    	Direccion direccion4 = new Direccion();
    	direccion4.setCalle("Rosario");
    	direccion4.setBarrio(caballito);
    	getSession().save(direccion4);
    	
    	
    	
    	Farmacia farmacia1 = new Farmacia();
    	farmacia1.setDiaDeTurno("Martes");
    	farmacia1.setNombre("Farmacity");
    	farmacia1.setDireccion(direccion1);
    	getSession().save(farmacia1);
    	
    	Farmacia farmacia2 = new Farmacia();
    	farmacia2.setDiaDeTurno("Lunes");
    	farmacia2.setNombre("DrAhorro");
    	farmacia2.setDireccion(direccion1);
    	getSession().save(farmacia2);
    	
    	Farmacia farmacia3 = new Farmacia();
    	farmacia3.setDiaDeTurno("Jueves");
    	farmacia3.setNombre("Dofarma");
    	farmacia3.setDireccion(direccion2);
    	getSession().save(farmacia3);
    	
    	Farmacia farmacia4 = new Farmacia();
    	farmacia4.setDiaDeTurno("Martes");
    	farmacia4.setNombre("Central Oeste");
    	farmacia4.setDireccion(direccion3);
    	getSession().save(farmacia4);
    	
    	Session session = getSession();
    	List<Farmacia> listaFarmaciasFloresta = session.createCriteria(Farmacia.class)
    			.createAlias("direccion.barrio", "buscaBarrio")
    			.add(Restrictions.eq("buscaBarrio.nombre","Floresta"))
    			.list();
    	
    	List<Farmacia> listaFarmaciasAlmagro = session.createCriteria(Farmacia.class)
    			.createAlias("direccion.barrio", "buscaBarrio")
    			.add(Restrictions.eq("buscaBarrio.nombre", "Almagro"))
    			.list();
    	
    	List<Farmacia> listaFarmaciasCaballito = session.createCriteria(Farmacia.class)
    			.createAlias("direccion.barrio", "buscaBarrio")
    			.add(Restrictions.eq("buscaBarrio.nombre", "Caballito"))
    			.list();
    	
    	List<Farmacia> listaTotales = session.createCriteria(Farmacia.class).list();
    	
    	assertEquals(4,listaTotales.size());
    	assertEquals(2,listaFarmaciasFloresta.size());
    	assertEquals(2,listaFarmaciasAlmagro.size());
    	assertEquals(0,listaFarmaciasCaballito.size());
    	
    	   	   	
    	
    }
    
    
}
