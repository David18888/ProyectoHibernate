package aed.hibernate;


import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.query.Query;
import aed.hibernate.table.*;
import java.util.Iterator;
import java.util.List;

public class Main {
	


	public static void main(String[] args) {

		
		Scanner entrada= new Scanner(System.in);
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		sesion.beginTransaction();
		
		
		//INSERTAMOS DATOS EN LAS TABLAS
		
		//TABLA LIGAS
		Ligas liga= new Ligas();
		liga.setCodLiga("LIG01");
		liga.setNomLiga("Primera División Nacional");
		sesion.save(liga);
		
		Ligas liga1= new Ligas();
		liga1.setCodLiga("LIG2A");
		liga1.setNomLiga("Segunda División Nacional");
		sesion.save(liga1);
		
		Ligas liga2= new Ligas();
		liga2.setCodLiga("LIG2B");
		liga2.setNomLiga("Segunda División B Nacional");
		sesion.save(liga2);
		
		
		//(liga a borrar posteriormente)
		Ligas liga3= new Ligas();
		liga3.setCodLiga("LIG00");
		liga3.setNomLiga("LIGA A BORRAR");
		sesion.save(liga3);
		
		
		//TABLA EQUIPOS
		Equipos equip = new Equipos();
		equip.setCodLiga("LIG01");
		equip.setNomEquipo("FC Barcelona");
		equip.setLocalidad("Barcelona");
		equip.setInternacional(1);
		sesion.save(equip);

		Equipos equipo2 = new Equipos();
		equipo2.setCodLiga("LIG2A");
		equipo2.setNomEquipo("CD Tenerife");
		equipo2.setLocalidad("Santa Cruz de Tenerife");
		equipo2.setInternacional(0);
		sesion.save(equipo2);
		
		
		Equipos equipo3 = new Equipos();
		equipo3.setCodLiga("LIG01");
		equipo3.setNomEquipo("Valencia CF");
		equipo3.setLocalidad("Valencia");
		equipo3.setInternacional(1);
		sesion.save(equipo3);
		
		
		
		
		
		
		
		//(Equipo que será borrado posteriormente)
		Equipos equip4 = new Equipos();
		equip4.setCodLiga("LIG2A");
		equip4.setNomEquipo("Real Madrid");
		equip4.setLocalidad("madrid");
		equip4.setInternacional(1);
		sesion.save(equip4);
		
		
		
		//TABLA FUTBOLISTAS
		
		Futbolistas futbolista= new Futbolistas();
		futbolista.setCodDNIoNIE("11111111A");
		futbolista.setNombre("Leo Messi");
		futbolista.setNacionalidad("Argentino");
		sesion.save(futbolista);
		
		Futbolistas futbolista2= new Futbolistas();
		futbolista2.setCodDNIoNIE("22222222A");
		futbolista2.setNombre("Suso Santana");
		futbolista2.setNacionalidad("Español");
		sesion.save(futbolista2);
		
		
		//TABLA CONTRATOS
		
		Contratos contrato= new Contratos();
		contrato.setCodContrato(1);
		contrato.setCodDNIoNIE("11111111A");
		contrato.setCodEquipo(1);
		contrato.setFechaInicio("2014-01-01");
		contrato.setFechaFin("2020-3-4");
		contrato.setPrecioAnual(12500);
		contrato.setPrecioRescision(14000);
		sesion.save(contrato);
		
		
		Contratos contrato2= new Contratos();
		contrato2.setCodContrato(2);
		contrato2.setCodDNIoNIE("22222222A");
		contrato2.setCodEquipo(1);
		contrato2.setFechaInicio("2009-01-01");
		contrato2.setFechaFin("2007-11-12");
		contrato2.setPrecioAnual(84000);
		contrato2.setPrecioRescision(21400);
		sesion.save(contrato2);
		
		
		//TABLA EQUIPOSOBSERVACIONES
		EquiposObservaciones observacion = new EquiposObservaciones();
		observacion.setCodEquipo(2);
		observacion.setObservaciones("Equipo venido a menos con el paso de los años");
		sesion.save(observacion);
		
		
		
		
		
		
		//ACTUALIZAR ALGUNOS REGISTROS
		
		equipo2.setLocalidad("TFE");
		sesion.update(equipo2);
		
		
		//ELIMINAR ALGUNOS REGISTROS

		sesion.delete(equip4);
		sesion.delete(liga3);	
	


		
		sesion.getTransaction().commit();
		sesion.close();
		System.out.println("\n\n--------------------------------REALIZADAS LA CREACIÓN DE LAS TABLAS E INSERCIONES PREVIAS A LA EJECUCIÓN DEL PROGRAMA-------------------------------------------");
		
		int opcion;
		
		
		do {
			Session sesionUser = HibernateUtil.getSessionFactory().openSession();
			sesionUser.beginTransaction();
			
			showMenu();
			opcion=entrada.nextInt();
			
			switch(opcion) {
			case 1:
				
				System.out.println("\n¿En qué tabla quieres insertar un registro?");
				System.out.println("---------------------------------------------");
				showTableOption();
				System.out.println("---------------------------------------------");
				System.out.print("Elige una opción: ");
				int tableOption=entrada.nextInt();
				switch (tableOption) {
				
				case 1: 
					String codliga,nomLiga;
					System.out.print("Escribe el código de la liga (5 caracteres): ");
					codliga=entrada.next();
					entrada.nextLine();
					System.out.print("Escribe el nombre de la liga (máx 40 caracteres): ");
					nomLiga=entrada.nextLine();
					
					if(codliga.length()>5 || nomLiga.length()>40 || nomLiga.isEmpty()) {
						System.err.println("No se ha podido insertar la liga.Comprueba los datos e inténtalo de nuevo. ");
					}
					else {
					
					Ligas insertaLiga= new Ligas();
					insertaLiga.setCodLiga(codliga);
					insertaLiga.setNomLiga(nomLiga);
					sesionUser.save(insertaLiga);
					sesionUser.getTransaction().commit();
					System.out.println("\nLiga insertada correctamente....");
					}
					
					
					break;
					
				case 2:
					try {
						String nomEquipo, codLiga, localidad;
						int internacional;
						
						//Cargamos un listado de las ligas disponibles para mostrarlas cuando queramos añadirlas a un equipo.
						String  ligas= cargarLigas();
					
					System.out.print("Escribe el nombre del equipo: ");
					entrada.nextLine();
					nomEquipo=entrada.nextLine();
					System.out.print("Escribe la liga a la que pertenece:" +ligas+ ":");
					codLiga=entrada.next();
					entrada.nextLine();
					System.out.print("Escribe la localidad a la que pertenece: ");
					localidad=entrada.nextLine();
					System.out.print("¿Es internacional?(0=NO/1:SÍ): ");
					internacional=entrada.nextInt();
					
					
					
					boolean ligaExiste=ligas.contains(codLiga);
					if(ligaExiste) {
					
					Equipos insertaEquipo= new Equipos();
					insertaEquipo.setCodLiga(codLiga);
					insertaEquipo.setNomEquipo(nomEquipo);
					insertaEquipo.setLocalidad(localidad);
					insertaEquipo.setInternacional(internacional);
					sesionUser.save(insertaEquipo);
					sesionUser.getTransaction().commit();	
					System.out.println("\nEquipo insertado correctamente...");
					}
					else {
						System.err.println("\nNo se ha podido insertar el equipo.Comprueba los datos e inténtalo de nuevo.");
					}
					
					}catch(Exception e){
						System.err.println("\nNo se ha podido insertar el equipo.Comprueba los datos e inténtalo de nuevo.");
					}
				
					break;
				
				case 3:
					try {
						
						String dni,nombre,nacionalidad;
						System.out.print("Escribe el DNI del futbolista (9 caracteres): ");
						entrada.nextLine();
						dni=entrada.nextLine();
						System.out.print("Escribe el nombre del futbolista: ");
						nombre=entrada.nextLine();
						System.out.print("Escribe su nacionalidad: ");
						nacionalidad=entrada.next();
						
					
						
						
						Futbolistas insertaFutbolista= new Futbolistas();
						insertaFutbolista.setCodDNIoNIE(dni);
						insertaFutbolista.setNombre(nombre);
						insertaFutbolista.setNacionalidad(nacionalidad);
						sesionUser.save(insertaFutbolista);
						sesionUser.getTransaction().commit();
						System.out.println("\nFutbolista insertado correctamente...");
						
						}catch(Exception e) {
							System.err.println("\nNo se ha podido insertar el futbolista.Comprueba los datos e inténtalo de nuevo.");
						}
					
						
						break;
						
				case 4:	
					try {
						
						int codEquipoIn,precioAnual,precioRescision;
						String fechaInicioIn,fechaFinIn, dniIn;
						
						System.out.print("Escribe el DNI del futbolista  al que le asignaremos un contrato: ");
						dniIn=entrada.next();
						entrada.nextLine();
						System.out.print("Escribe el código del equipo: ");
						codEquipoIn=entrada.nextInt();
						entrada.nextLine();
						System.out.print("Escribe la fecha de inicio del contrato (aaaa/mm/dd): ");
						fechaInicioIn=entrada.next();
						entrada.nextLine();
						System.out.print("Escribe la fecha de finalización del contrato(aaaa/mm/dd): ");
						fechaFinIn=entrada.next();
						entrada.nextLine();
						System.out.print("Escribe su precio anual: ");
						precioAnual=entrada.nextInt();
						entrada.nextLine();
						System.out.print("Escribe su precio de rescisión: ");
						precioRescision=entrada.nextInt();
						
			
						Contratos insertaContrato= new Contratos();
						insertaContrato.setCodDNIoNIE(dniIn);
						insertaContrato.setCodEquipo(codEquipoIn);
						insertaContrato.setFechaInicio(fechaInicioIn);
						insertaContrato.setFechaFin(fechaFinIn);
						insertaContrato.setPrecioAnual(precioAnual);
						insertaContrato.setPrecioRescision(precioRescision);
						sesionUser.save(insertaContrato);
						sesionUser.getTransaction().commit();
						System.out.println("Contrato insertado...");
						}
						catch(Exception e) {
							System.err.println("\nError al insertar el contrato.Comprueba los datos e inténtalo de nuevo");
						}
						
						
						break;
				case 5:
					int cod;
					String obs;
					System.out.print("Escribe el código del equipo: ");
					cod=entrada.nextInt();
					entrada.nextLine();

					System.out.print("Escribe la observación: ");
					obs=entrada.nextLine();
					
					try {
						EquiposObservaciones insertaObs= new EquiposObservaciones();
						insertaObs.setCodEquipo(cod);
						insertaObs.setObservaciones(obs);
						sesionUser.save(insertaObs);
						sesionUser.getTransaction().commit();
						System.out.println("\nObservación insertada al equipo con código: "+cod);
						
					}
					
					catch(Exception e) {
						System.err.println("\nNo se ha podido insertar la observación porque el código especificado no corresponde a ningún equipo o bien ese equipo ya tiene asignada una observación");
					}
					
					break;
					
					default:break;
					
				}
				
				break;
				
		
			case 2:
				System.out.println("\n¿De qué tabla quieres eliminar un registro?");
				System.out.println("--------------------------------------------");
				showTableOption();
				System.out.println("-----------------");
				System.out.print("Elige una opcion: ");
				int table=entrada.nextInt();
				
				switch(table) {
				case 1:
					try {

					String listaLigas= cargarLigas();	
					System.out.print("Escribe el código de la liga que quieres eliminar("+listaLigas+"):");
					String eliminada=entrada.next();
					entrada.nextLine();
					String borraLigaQ="delete Ligas lg where lg.codLiga=?0";
					int ligasBorrada=sesionUser.createQuery( borraLigaQ ) .setString( 0, eliminada ).executeUpdate();
					sesionUser.getTransaction().commit();
					if (ligasBorrada>=1)
					System.out.println("\nLiga eliminada...");
					else 
						System.err.println("\nNo se ha podido borrar la Liga. Comprueba los datos e inténtalo de nuevo");
					}
					
					catch(Exception e) {
						System.err.println("\nNo se ha podido borrar la Liga. Comprueba los datos e inténtalo de nuevo");
					}
					break;
					
				case 2: 
					try {
				String cargaEquipos=cargarEquipos();	
				System.out.print("Escribe el nombre del equipo que quieres eliminar("+cargaEquipos+"): ");
				String eliminado= entrada.next();
				entrada.nextLine();
				String borraEquipoQ="delete Equipos eq where eq.nomEquipo=?0";
				int equiposBorrado=sesionUser.createQuery( borraEquipoQ ) .setString( 0, eliminado ).executeUpdate();
				sesionUser.getTransaction().commit();
				if (equiposBorrado>=1)
					System.out.println("\nEquipo eliminado...");
					else 
						System.err.println("\nNo se ha podido borrar el equipo. Comprueba los datos e inténtalo de nuevo.");
					}catch(Exception e) {
						System.err.println("\nNo se ha podido borrar el equipo. Comprueba los datos e inténtalo de nuevo.");
					}
				
					break;	
				case 3:
					try {
					System.out.print("Escribe el DNI  del jugador que quieres eliminar: ");
					String jugEliminado=entrada.next();
					String borraJugadorQ="delete Futbolistas fb where fb.codDNIoNIE=?0";
					int jugadorBorrado=sesionUser.createQuery(borraJugadorQ).setString(0, jugEliminado).executeUpdate();
					if(jugadorBorrado>=1)
						System.out.println("\nJugador eliminado...");
					else
						System.err.println("\nNo se ha podido borrar el jugador. Comprueba los datos e inténtalo de nuevo.");
					}catch(Exception e) {
						System.err.println("\nNo se ha podido borrar el jugador.Comprueba los datos e inténtalo de nuevo.");
					}
					break;
				case 4:
					try {
						System.out.print("Escribe el código del contrato que quieres eliminar: ");
						int contratoBorrado=entrada.nextInt();
						String borraContratoQ="delete Contratos ct where ct.codContrato=?0";
						int ctBorrado=sesionUser.createQuery(borraContratoQ).setInteger(0, contratoBorrado).executeUpdate();
						if(ctBorrado>=1)
							System.out.println("\nContrato eliminado...");
						else {
							System.err.println("\nNo se ha podido borrar el contrato.Comprueba los datos e inténtalo de nuevo.");
						}
						
					}catch(Exception e) {
						System.err.println("\nNo se ha podido borrar el contrato.Comprueba los datos e inténtalo de nuevo.");
					}
					break;
				case 5:	
					try {
						System.out.print("Escribe el código de la observación que quieres eliminar: ");
						int codObeliminado=entrada.nextInt();
						String borraObservacionQ="delete EquiposObservaciones ob where ob.codEquipo=?0";
						int observaBorrado=sesionUser.createQuery(borraObservacionQ).setInteger(0, codObeliminado).executeUpdate();
						if(observaBorrado>=1)
							System.out.println("\nObservación borrada....");
						else
							System.err.println("\nNo se ha podido borrar la observación.Comprueba los datos e inténtalo de nuevo");
							
						}catch(Exception e) {
							
							System.err.println("\nNo se ha podido borrar la observación.Comprueba los datos e inténtalo de nuevo");

					}
					
					
					
					break;
					
					default: break;
	
				
				}
				
			  break;
				
			
			case 3:
				System.out.println("\n¿Qué tabla deseas consultar?");
				System.out.println("-----------------------------");
				showTableOption();
				System.out.println("----------------------------");
				System.out.print("Escribe una opción: ");
				int consultaOption=entrada.nextInt();
				switch(consultaOption) {
				case 1: 
					showLigas();
					break;
				case 2:
					showEquipos();
					break;
				case 3:
					showFutbolistas();
					break;
				case 4:
					showContratos();
					break;
				case 5:
					showObservaciones();
					break;
				
				default:break;
				
				}
				
				break;
				
			
			case 4: 
				Query  qEquipos = sesionUser.createQuery("FROM Equipos e left join e.equipo");
			    Iterator it= qEquipos.iterate();
			
				while(it.hasNext()) {
					Object[] par=(Object[]) it.next();
					Equipos equi=(Equipos)par[0];
					EquiposObservaciones equipObv=(EquiposObservaciones)par[1];
					System.out.println();
					if(equipObv==null) {
						equipObv= new EquiposObservaciones();
						equipObv.setObservaciones(" ");
					}	
			
					System.out.print("|"+equi.getCodEquipo() + "|" + equi.getNomEquipo() + "|" + equi.getCodLiga() + "|"
							+ equi.getLocalidad() + "|" + equi.getInternacional()+"|"+equipObv.getObservaciones()+"|\n");
					
				}
				
				
					
				break;
			
			case 5:
				Query qContratos= sesionUser.createQuery("FROM Contratos c inner join c.futbolista inner join c.equipo");
				Iterator itC= qContratos.iterate();
				
				while(itC.hasNext()) {
					
					Object[] contratosObject= (Object[])itC.next();
					Contratos contr= (Contratos)contratosObject[0];
					Futbolistas fb= (Futbolistas)contratosObject[1];
					Equipos eq=(Equipos)contratosObject[2];
					
					
					
					System.out.println(contr.getCodContrato()+"|"+contr.getCodDNIoNIE()+"|"+fb.getNombre()+"|"+contr.getCodEquipo()+"|"+eq.getNomEquipo()+
							"|"+contr.getFechaInicio()+"|"+contr.getFechaFin()+"|"+contr.getPrecioAnual()+"|"+contr.getPrecioRescision());
					
					
				}
				break;	
				
				
				default: break;
				
				
			}
			
			

			sesionUser.close();

		}while(opcion!=6);
		System.out.println("Programa finalizado.....");
		entrada.close();


	}

	
	
//MÉTODOS PARA MOSTRAR LOS DATOS DE CADA UNA DE LAS TABLAS	
private static void showLigas() {
		Session consultaLGsesion=HibernateUtil.getSessionFactory().openSession();
		Query<Ligas>consultaLigas=consultaLGsesion.createQuery("FROM Ligas ");
		List<Ligas>listaLigas=consultaLigas.getResultList();
		
		for(Ligas lg: listaLigas) {
			System.out.println("|"+lg.getCodLiga()+"|"+lg.getNomLiga()+"|");
		}
		
		consultaLGsesion.close();
		
	}

private static void showEquipos() {
	Session consultaEQsesion=HibernateUtil.getSessionFactory().openSession();
	Query<Equipos>consultaEquipos=consultaEQsesion.createQuery("FROM Equipos ");
	List<Equipos>listaEquipos=consultaEquipos.getResultList();
	
	for(Equipos eq: listaEquipos) {
		
		System.out.println("|"+eq.getCodEquipo()+"|"+eq.getNomEquipo()+"|"+eq.getCodLiga()+"|"+eq.getLocalidad()+"|"+eq.getInternacional());
	}
	consultaEQsesion.close();
}

private static void showFutbolistas() {

	Session consultaFTsesion=HibernateUtil.getSessionFactory().openSession();
	Query<Futbolistas>consultaFutbolistas=consultaFTsesion.createQuery("FROM Futbolistas ");
	List<Futbolistas>listaFutbolistas=consultaFutbolistas.getResultList();
	
	for(Futbolistas ft: listaFutbolistas) {
		
		System.out.println("|"+ft.getCodDNIoNIE()+"|"+ft.getNombre()+"|"+ft.getNacionalidad());
	}
	
	consultaFTsesion.close();
	
}


private static void showContratos() {
	Session consultaCTsesion=HibernateUtil.getSessionFactory().openSession();
	Query<Contratos>consultaContratos=consultaCTsesion.createQuery("FROM Contratos ");
	List<Contratos>listaContratos=consultaContratos.getResultList();
	
	for(Contratos ct: listaContratos) {
		
		System.out.println("|"+ct.getCodContrato()+"|"+ct.getCodDNIoNIE()+"|"+ct.getCodEquipo()+"|"+ct.getFechaInicio()+"|"+ct.getFechaFin()+"|"+ct.getPrecioAnual()+"|"+ct.getPrecioRescision());
	}
	
	consultaCTsesion.close();
	
}


private static void showObservaciones(){
	
	
	Session consultaOBsesion=HibernateUtil.getSessionFactory().openSession();
	Query<EquiposObservaciones>consultaObservaciones=consultaOBsesion.createQuery("FROM EquiposObservaciones ");
	List<EquiposObservaciones>listaObservaciones=consultaObservaciones.getResultList();
	
	for(EquiposObservaciones ob: listaObservaciones) {
		
		System.out.println("|"+ob.getCodEquipo()+"|"+ob.getObservaciones());
	}
	
	consultaOBsesion.close();
}



//ALGUNOS MÉTODOS PARA CARGAR REGISTROS Y SERVIR DE AYUDA AL USUARIO AL INSERTAR/BORRAR
private static String cargarLigas() {
		Session sesionLigas = HibernateUtil.getSessionFactory().openSession();
		sesionLigas.beginTransaction();
		String ligasList="";
		
		Query<Ligas> q = sesionLigas.createQuery("SELECT l.codLiga FROM Ligas as l");
		 ligasList+= q.getResultList().toString();
		
	sesionLigas.close();
	return ligasList;
	
	}



private static String cargarEquipos() {
	Session sesionEquipos=HibernateUtil.getSessionFactory().openSession();
	sesionEquipos.beginTransaction();
	String equiposList="";
	Query<Equipos>q= sesionEquipos.createQuery("SELECT eq.nomEquipo FROM Equipos as eq");
	equiposList+=q.getResultList().toString();
	sesionEquipos.close();
	return equiposList;
	
	
}



//MENU DE ELECCIÓN DE TABLA
private static void showTableOption() {
		System.out.println("1.Ligas");
		System.out.println("2.Equipos");
		System.out.println("3.Futbolistas");
		System.out.println("4.Contratos");
		System.out.println("5.Observaciones");
		
	}


//MENU PRINCIPAL
private static  void showMenu() {
	System.out.println("\n\n\nBASE DE DATOS FUTBOLBD CON HIBERNATE DAVID DÍAZ BARRIOS");
	System.out.println("-------------------------------------");
	System.out.println("1.Insertar un registro");
	System.out.println("2.Borrar un registro ");
	System.out.println("3.Consultar una tabla");
	System.out.println("4.Visualizar los datos de la tabla equipos con sus observaciones");
	System.out.println("5.Visualizar los datos de la tabla contratos incluyendo futbolistas y liga");
	System.out.println("6.Salir");
	System.out.println("---------------------------------------");
	System.out.print("Elige una opción: ");
	
	
	
}
	


}
