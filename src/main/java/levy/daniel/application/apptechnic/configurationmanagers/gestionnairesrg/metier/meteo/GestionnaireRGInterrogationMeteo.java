package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.metier.meteo;

import java.net.MalformedURLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.GestionnaireRG;

/**
 * class GestionnaireRGInterrogationMeteo :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * Manager, Configurateur, Règle de Gestion, RG, <br/>
 * délégation, délegué, <br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 26 sept. 2017
 *
 */
public final class GestionnaireRGInterrogationMeteo {

	// ************************ATTRIBUTS************************************/

	/**
	 * validerPaysInterrogationMeteo : Boolean :<br/>
	 * Boolean activant globalement les contrôles 
	 * sur le Pays du InterrogationMeteo.<br/>
	 */
	private static Boolean validerPaysInterrogationMeteo;

	
	/**
	 * validerRGInterroPays01 : Boolean :<br/>
	 * Boolean activant la RG_INTERRO_PAYS_01 : 
	 * "le pays doit être renseigné".<br/>
	 */
	private static Boolean validerRGInterroPays01;
	

	/**
	 * validerRGInterroPays02 : Boolean :<br/>
	 * Boolean activant la RG_INTERRO_PAYS_02 : 
	 * "le pays ne doit contenir que des lettres".<br/>
	 */
	private static Boolean validerRGInterroPays02;

	
	/**
	 * validerVilleInterrogationMeteo : Boolean :<br/>
	 * Boolean activant globalement les contrôles 
	 * sur la Ville du InterrogationMeteo.<br/>
	 */
	private static Boolean validerVilleInterrogationMeteo;

	
	/**
	 * validerRGInterroVille03 : Boolean :<br/>
	 * Boolean activant la RG_INTERRO_VILLE_03 : 
	 * "la Ville doit être renseignée".<br/>
	 */
	private static Boolean validerRGInterroVille03;
	

	/**
	 * validerRGInterroVille04 : Boolean :<br/>
	 * Boolean activant la RG_INTERRO_VILLE_04 : 
	 * "la Ville ne doit contenir que des lettres".<br/>
	 */
	private static Boolean validerRGInterroVille04;

	
	/**
	 * validerNbreJoursInterrogationMeteo : Boolean :<br/>
	 * Boolean activant globalement les contrôles 
	 * sur le NbreJours du InterrogationMeteo.<br/>
	 */
	private static Boolean validerNbreJoursInterrogationMeteo;

	
	/**
	 * validerRGInterroNbreJours05 : Boolean :<br/>
	 * Boolean activant la RG_INTERRO_NBREJOURS_05 : 
	 * "le nombre de jours doit être homogène à un entier numérique".<br/>
	 */
	private static Boolean validerRGInterroNbreJours05;
	

	/**
	 * validerRGInterroNbreJours06 : Boolean :<br/>
	 * Boolean activant la RG_INTERRO_NBREJOURS_06 : 
	 * "le nombre de jours doit être inférieur ou égal à 10".<br/>
	 */
	private static Boolean validerRGInterroNbreJours06;


	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(GestionnaireRGInterrogationMeteo.class);
	

	// *************************METHODES************************************/
	
	 /**
	 * method CONSTRUCTEUR GestionnaireRGInterrogationMeteo() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	private GestionnaireRGInterrogationMeteo() {		
		super();				
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * method lireControlePaysActif() :<br/>
	 * <ul>
	 * Lit dans rg.properties les valeurs de : 
	 * <li>validerPaysInterrogationMeteo</li>
	 * <li>validerRGInterroPays01</li>
	 * <li>validerRGInterroPays02</li>
	 * </ul>
	 * Trim() les valeurs dans le properties.<br/>
	 * <br/>
	 * 
	 * @throws MalformedURLException 
	 */
	private static void lireControlePaysActif() 
			throws MalformedURLException {
		
		synchronized (GestionnaireRGInterrogationMeteo.class) {
			
			/* validation globale sur le pays : 
			 * validerPaysInterrogationMeteo. */
			if (validerPaysInterrogationMeteo == null) {
				
				final String validerPaysInterrogationMeteoString 
					= GestionnaireRG.getBundleExterneRG()
						.getString(fournirCleControlePays()); 
				
				validerPaysInterrogationMeteo 
					= Boolean.valueOf(
							validerPaysInterrogationMeteoString.trim());
				
			}
			
			/* validerRGInterroPays01. */
			if (validerRGInterroPays01 == null) {
				
				final String validerRGInterroPays01String 
					= GestionnaireRG.getBundleExterneRG()
						.getString(fournirCleControleRGInterroPays01());
				
				validerRGInterroPays01 
					= Boolean.valueOf(validerRGInterroPays01String.trim());
				
			}
			
			/* validerRGInterroPays02. */
			if (validerRGInterroPays02 == null) {
				
				final String validerRGInterroPays02String 
					= GestionnaireRG.getBundleExterneRG()
						.getString(fournirCleControleRGInterroPays02());
				
				validerRGInterroPays02 
					= Boolean.valueOf(validerRGInterroPays02String.trim());
				
			}
			
		} // Fin de bloc synchronized.__________________________
				
	} // Fin de lireControlePaysActif().___________________________________


	
	/**
	 * method fournirCleControlePays() :<br/>
	 * Retourne la clé de validerPaysInterrogationMeteo 
	 * dans rg.properties.<br/>
	 * "interrogationmeteo.pays.actif".<br/>
	 * <br/>
	 *
	 * @return : String : "interrogationmeteo.pays.actif".<br/>
	 */
	private static String fournirCleControlePays() {
		return "interrogationmeteo.pays.actif";
	} // Fin de fournirCleControlePays().__________________________________
	

	
	/**
	 * method fournirCleControleRGInterroPays01() :<br/>
	 * Retourne la clé de validerRGInterroPays01 dans rg.properties.<br/>
	 * "interrogationmeteo.pays.rginterropays01.actif".<br/>
	 * <br/>
	 *
	 * @return : String : "interrogationmeteo.pays.rginterropays01.actif".<br/>
	 */
	private static String fournirCleControleRGInterroPays01() {
		return "interrogationmeteo.pays.rginterropays01.actif";
	} // Fin de fournirCleControleRGInterroPays01()._______________________
	
	

	/**
	 * method fournirCleControleRGInterroPays02() :<br/>
	 * Retourne la clé de validerRGInterroPays02 dans rg.properties.<br/>
	 * "interrogationmeteo.pays.rginterropays02.actif".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "interrogationmeteo.pays.rginterropays02.actif".<br/>
	 */
	private static String fournirCleControleRGInterroPays02() {
		return "interrogationmeteo.pays.rginterropays02.actif";
	} // Fin de fournirCleControleRGInterroPays02()._________________________
	

		
	/**
	 * method lireControleVilleActif() :<br/>
	 * <ul>
	 * Lit dans rg.properties les valeurs de : 
	 * <li>validerVilleInterrogationMeteo</li>
	 * <li>validerRGInterroVille03</li>
	 * <li>validerRGInterroVille04</li>
	 * </ul>
	 * Trim() les valeurs dans le properties.<br/>
	 * <br/>
	 * 
	 * @throws MalformedURLException 
	 */
	private static void lireControleVilleActif() 
			throws MalformedURLException {
		
		synchronized (GestionnaireRGInterrogationMeteo.class) {
			
			/* validation globale sur la Ville : 
			 * validerVilleInterrogationMeteo. */
			if (validerVilleInterrogationMeteo == null) {
				
				final String validerVilleInterrogationMeteoString 
					= GestionnaireRG.getBundleExterneRG()
						.getString(fournirCleControleVille()); 
				
				validerVilleInterrogationMeteo 
					= Boolean.valueOf(
							validerVilleInterrogationMeteoString.trim());
				
			}
			
			/* validerRGInterroVille03. */
			if (validerRGInterroVille03 == null) {
				
				final String validerRGInterroVille01String 
					= GestionnaireRG.getBundleExterneRG()
						.getString(fournirCleControleRGInterroVille03());
				
				validerRGInterroVille03 
					= Boolean.valueOf(validerRGInterroVille01String.trim());
				
			}
			
			/* validerRGInterroVille04. */
			if (validerRGInterroVille04 == null) {
				
				final String validerRGInterroVille02String 
					= GestionnaireRG.getBundleExterneRG()
						.getString(fournirCleControleRGInterroVille04());
				
				validerRGInterroVille04 
					= Boolean.valueOf(validerRGInterroVille02String.trim());
				
			}
			
		} // Fin de bloc synchronized.__________________________
						
	} // Fin de lireControleVilleActif().__________________________________
	
	
	
	/**
	 * method fournirCleControleVille() :<br/>
	 * Retourne la clé de validerVilleInterrogationMeteo 
	 * dans rg.properties.<br/>
	 * "interrogationmeteo.ville.actif".<br/>
	 * <br/>
	 *
	 * @return : String : "interrogationmeteo.ville.actif".<br/>
	 */
	private static String fournirCleControleVille() {
		return "interrogationmeteo.ville.actif";
	} // Fin de fournirCleControleVille()._________________________________
	

	
	/**
	 * method fournirCleControleRGInterroVille03() :<br/>
	 * Retourne la clé de validerRGInterroVille03 dans rg.properties.<br/>
	 * "interrogationmeteo.ville.rginterroville03.actif".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "interrogationmeteo.ville.rginterroville03.actif".<br/>
	 */
	private static String fournirCleControleRGInterroVille03() {
		return "interrogationmeteo.ville.rginterroville03.actif";
	} // Fin de fournirCleControleRGInterroVille03().______________________
	
	

	/**
	 * method fournirCleControleRGInterroVille04() :<br/>
	 * Retourne la clé de validerRGInterroVille04 dans rg.properties.<br/>
	 * "interrogationmeteo.ville.rginterroville04.actif".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "interrogationmeteo.ville.rginterroville04.actif".<br/>
	 */
	private static String fournirCleControleRGInterroVille04() {
		return "interrogationmeteo.ville.rginterroville04.actif";
	} // Fin de fournirCleControleRGInterroVille04().______________________
	

			
	/**
	 * method lireControleNbreJoursActif() :<br/>
	 * <ul>
	 * Lit dans rg.properties les valeurs de : 
	 * <li>validerNbreJoursInterrogationMeteo</li>
	 * <li>validerRGInterroNbreJours05</li>
	 * <li>validerRGInterroNbreJours06</li>
	 * </ul>
	 * Trim() les valeurs dans le properties.<br/>
	 * <br/>
	 * 
	 * @throws MalformedURLException 
	 */
	private static void lireControleNbreJoursActif() 
			throws MalformedURLException {
		
		synchronized (GestionnaireRGInterrogationMeteo.class) {
			
			/* validation globale sur la NbreJours : 
			 * validerNbreJoursInterrogationMeteo. */
			if (validerNbreJoursInterrogationMeteo == null) {
				
				final String validerNbreJoursInterrogationMeteoString 
					= GestionnaireRG.getBundleExterneRG()
						.getString(fournirCleControleNbreJours()); 
				
				validerNbreJoursInterrogationMeteo 
					= Boolean.valueOf(
							validerNbreJoursInterrogationMeteoString.trim());
				
			}
			
			/* validerRGInterroNbreJours05. */
			if (validerRGInterroNbreJours05 == null) {
				
				final String validerRGInterroNbreJours05String 
					= GestionnaireRG.getBundleExterneRG()
						.getString(fournirCleControleRGInterroNbreJours05());
				
				validerRGInterroNbreJours05 
					= Boolean.valueOf(validerRGInterroNbreJours05String.trim());
				
			}
			
			/* validerRGInterroNbreJours06. */
			if (validerRGInterroNbreJours06 == null) {
				
				final String validerRGInterroNbreJours06String 
					= GestionnaireRG.getBundleExterneRG()
						.getString(fournirCleControleRGInterroNbreJours06());
				
				validerRGInterroNbreJours06 
					= Boolean.valueOf(validerRGInterroNbreJours06String.trim());
				
			}
			
		} // Fin de bloc synchronized.__________________________
						
	} // Fin de lireControleNbreJoursActif().__________________________________
	
	
	
	/**
	 * method fournirCleControleNbreJours() :<br/>
	 * Retourne la clé de validerNbreJoursInterrogationMeteo 
	 * dans rg.properties.<br/>
	 * "interrogationmeteo.nbrejours.actif".<br/>
	 * <br/>
	 *
	 * @return : String : "interrogationmeteo.nbrejours.actif".<br/>
	 */
	private static String fournirCleControleNbreJours() {
		return "interrogationmeteo.nbrejours.actif";
	} // Fin de fournirCleControleNbreJours()._____________________________
	
	
	
	/**
	 * method fournirCleControleRGInterroNbreJours05() :<br/>
	 * Retourne la clé de validerRGInterroNbreJours05 
	 * dans rg.properties.<br/>
	 * "interrogationmeteo.nbrejours.rginterronbrejours05.actif".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "interrogationmeteo.nbrejours.rginterronbrejours05.actif".<br/>
	 */
	private static String fournirCleControleRGInterroNbreJours05() {
		return "interrogationmeteo.nbrejours.rginterronbrejours05.actif";
	} // Fin de fournirCleControleRGInterroNbreJours05().__________________
	
	
	
	/**
	 * method fournirCleControleRGInterroNbreJours06() :<br/>
	 * Retourne la clé de validerRGInterroNbreJours06 dans rg.properties.<br/>
	 * "interrogationmeteo.nbrejours.rginterronbrejours06.actif".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "interrogationmeteo.nbrejours.rginterronbrejours06.actif".<br/>
	 */
	private static String fournirCleControleRGInterroNbreJours06() {
		return "interrogationmeteo.nbrejours.rginterronbrejours06.actif";
	} // Fin de fournirCleControleRGInterroNbreJours06().__________________
	

	
	/**
	 * method getValiderPaysInterrogationMeteo() :<br/>
	 * Getter du Boolean activant globalement 
	 * les contrôles sur le Pays du InterrogationMeteo.<br/>
	 * <br/>
	 *
	 * @return validerPaysInterrogationMeteo : Boolean.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public static Boolean getValiderPaysInterrogationMeteo() 
				throws MalformedURLException {
		
		if (validerPaysInterrogationMeteo == null) {
			lireControlePaysActif();
		}
		
		return validerPaysInterrogationMeteo;
		
	} // Fin de getValiderPaysInterrogationMeteo().________________________

	
	
	/**
	* method setValiderPaysInterrogationMeteo(
	* Boolean pValiderPaysInterrogationMeteo) :<br/>
	* Setter du Boolean activant globalement 
	* les contrôles sur le pays du InterrogationMeteo.<br/>
	* <br/>
	*
	* @param pValiderPaysInterrogationMeteo : Boolean : 
	* valeur à passer à validerPaysInterrogationMeteo.<br/>
	*/
	public static void setValiderPaysInterrogationMeteo(
			final Boolean pValiderPaysInterrogationMeteo) {
		validerPaysInterrogationMeteo = pValiderPaysInterrogationMeteo;
	} // fin de setValiderPaysInterrogationMeteo(...)._____________________


	
	/**
	 * method getValiderRGInterroPays01() :<br/>
	 * Getter du Boolean activant la RG_INTERRO_PAYS_01 : 
	 * "le pays doit être renseigné".<br/>
	 * <br/>
	 *
	 * @return validerRGInterroPays01 : Boolean.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public static Boolean getValiderRGInterroPays01() 
			throws MalformedURLException {
		
		if (validerRGInterroPays01 == null) {
			lireControlePaysActif();
		}
		
		return validerRGInterroPays01;
		
	} // Fin de getValiderRGInterroPays01()._______________________________


	
	/**
	* method setValiderRGInterroPays01(
	* Boolean pValiderRGInterroPays01) :<br/>
	* Setter du Boolean activant la RG_INTERRO_PAYS_01 : 
	* "le pays doit être renseigné".<br/>
	* <br/>
	*
	* @param pValiderRGInterroPays01 : Boolean : 
	* valeur à passer à validerRGInterroPays01.<br/>
	*/
	public static void setValiderRGInterroPays01(
			final Boolean pValiderRGInterroPays01) {
		validerRGInterroPays01 = pValiderRGInterroPays01;
	} // Fin de setValiderRGInterroPays01(...).____________________________


	
	/**
	 * method getValiderRGInterroPays02() :<br/>
	 * Getter du Boolean activant la RG_INTERRO_PAYS_02 : 
	 * "le pays ne doit contenir que des lettres".<br/>
	 * <br/>
	 *
	 * @return validerRGInterroPays02 : Boolean.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public static Boolean getValiderRGInterroPays02() 
			throws MalformedURLException {
		
		if (validerRGInterroPays02 == null) {
			lireControlePaysActif();
		}
		
		return validerRGInterroPays02;
		
	} // Fin de getValiderRGInterroPays02()._______________________________


	
	/**
	* method setValiderRGInterroPays02(
	* Boolean pValiderRGInterroPays02) :<br/>
	* Setter du Boolean activant la RG_INTERRO_PAYS_02 : 
	* "le pays ne doit contenir que des lettres".<br/>
	* <br/>
	*
	* @param pValiderRGInterroPays02 : Boolean : 
	* valeur à passer à validerRGInterroPays02.<br/>
	*/
	public static void setValiderRGInterroPays02(
			final Boolean pValiderRGInterroPays02) {
		validerRGInterroPays02 = pValiderRGInterroPays02;
	} // Fin de setValiderRGInterroPays02(...).____________________________


		
	/**
	 * method getValiderVilleInterrogationMeteo() :<br/>
	 * Getter du Boolean activant globalement 
	 * les contrôles sur la Ville du InterrogationMeteo.<br/>
	 * <br/>
	 *
	 * @return validerVilleInterrogationMeteo : Boolean.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public static Boolean getValiderVilleInterrogationMeteo() 
				throws MalformedURLException {
		
		if (validerVilleInterrogationMeteo == null) {
			lireControleVilleActif();
		}
		
		return validerVilleInterrogationMeteo;
		
	} // Fin de getValiderVilleInterrogationMeteo()._______________________

	
	
	/**
	* method setValiderVilleInterrogationMeteo(
	* Boolean pValiderVilleInterrogationMeteo) :<br/>
	* Setter du Boolean activant globalement 
	* les contrôles sur la Ville du InterrogationMeteo.<br/>
	* <br/>
	*
	* @param pValiderVilleInterrogationMeteo : Boolean : 
	* valeur à passer à validerVilleInterrogationMeteo.<br/>
	*/
	public static void setValiderVilleInterrogationMeteo(
			final Boolean pValiderVilleInterrogationMeteo) {
		validerVilleInterrogationMeteo = pValiderVilleInterrogationMeteo;
	} // fin de setValiderVilleInterrogationMeteo(...).____________________


	
	/**
	 * method getValiderRGInterroVille03() :<br/>
	 * Getter du Boolean activant la RG_INTERRO_VILLE_03 : 
	 * "la Ville doit être renseignée".<br/>
	 * <br/>
	 *
	 * @return validerRGInterroVille03 : Boolean.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public static Boolean getValiderRGInterroVille03() 
			throws MalformedURLException {
		
		if (validerRGInterroVille03 == null) {
			lireControleVilleActif();
		}
		
		return validerRGInterroVille03;
		
	} // Fin de getValiderRGInterroVille03().______________________________


	
	/**
	* method setValiderRGInterroVille03(
	* Boolean pValiderRGInterroVille03) :<br/>
	* Setter du Boolean activant la RG_INTERRO_VILLE_03 : 
	* "la Ville doit être renseignée".<br/>
	* <br/>
	*
	* @param pValiderRGInterroVille03 : Boolean : 
	* valeur à passer à validerRGInterroVille03.<br/>
	*/
	public static void setValiderRGInterroVille03(
			final Boolean pValiderRGInterroVille03) {
		validerRGInterroVille03 = pValiderRGInterroVille03;
	} // Fin de setValiderRGInterroVille03(...).___________________________


	
	/**
	 * method getValiderRGInterroVille04() :<br/>
	 * Getter du Boolean activant la RG_INTERRO_VILLE_04 : 
	 * "la Ville ne doit contenir que des lettres".<br/>
	 * <br/>
	 *
	 * @return validerRGInterroVille04 : Boolean.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public static Boolean getValiderRGInterroVille04() 
			throws MalformedURLException {
		
		if (validerRGInterroVille04 == null) {
			lireControleVilleActif();
		}
		
		return validerRGInterroVille04;
		
	} // Fin de getValiderRGInterroVille04().______________________________


	
	/**
	* method setValiderRGInterroVille04(
	* Boolean pValiderRGInterroVille04) :<br/>
	* Setter du Boolean activant la RG_INTERRO_VILLE_04 : 
	* "la Ville ne doit contenir que des lettres".<br/>
	* <br/>
	*
	* @param pValiderRGInterroVille04 : Boolean : 
	* valeur à passer à validerRGInterroVille04.<br/>
	*/
	public static void setValiderRGInterroVille04(
			final Boolean pValiderRGInterroVille04) {
		validerRGInterroVille04 = pValiderRGInterroVille04;
	} // Fin de setValiderRGInterroVille04(...).___________________________


	
	/**
	 * method getValiderNbreJoursInterrogationMeteo() :<br/>
	 * Getter du Boolean activant globalement les contrôles 
	 * sur le NbreJours du InterrogationMeteo.<br/>
	 * <br/>
	 *
	 * @return validerNbreJoursInterrogationMeteo : Boolean.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public static Boolean getValiderNbreJoursInterrogationMeteo() 
			throws MalformedURLException {
	
		if (validerNbreJoursInterrogationMeteo == null) {
			lireControleNbreJoursActif();
		}
		
		return validerNbreJoursInterrogationMeteo;
		
	} // Fin de getValiderNbreJoursInterrogationMeteo().___________________



	/**
	* method setValiderNbreJoursInterrogationMeteo(
	* Boolean pValiderNbreJoursInterrogationMeteo) :<br/>
	* Setter du Boolean activant globalement les contrôles 
	* sur le NbreJours du InterrogationMeteo.<br/>
	* <br/>
	*
	* @param pValiderNbreJoursInterrogationMeteo : Boolean : 
	* valeur à passer à validerNbreJoursInterrogationMeteo.<br/>
	*/
	public static void setValiderNbreJoursInterrogationMeteo(
			final Boolean pValiderNbreJoursInterrogationMeteo) {	
		validerNbreJoursInterrogationMeteo 
			= pValiderNbreJoursInterrogationMeteo;
	} // Fin de setValiderNbreJoursInterrogationMeteo(...).________________


	
	/**
	 * method getValiderRGInterroNbreJours05() :<br/>
	 * Getter du Boolean activant la RG_INTERRO_NBREJOURS_05 : 
	 * "le nombre de jours doit être homogène à un entier numérique".<br/>
	 * <br/>
	 *
	 * @return validerRGInterroNbreJours05 : Boolean.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public static Boolean getValiderRGInterroNbreJours05() 
			throws MalformedURLException {
	
		if (validerRGInterroNbreJours05 == null) {
			lireControleNbreJoursActif();
		}
		
		return validerRGInterroNbreJours05;
		
	} // Fin de getValiderRGInterroNbreJours05().__________________________


	
	/**
	* method setValiderRGInterroNbreJours05(
	* Boolean pValiderRGInterroNbreJours05) :<br/>
	* Setter du Boolean activant la RG_INTERRO_NBREJOURS_05 : 
	* "le nombre de jours doit être homogène à un entier numérique".<br/>
	* <br/>
	*
	* @param pValiderRGInterroNbreJours05 : Boolean : 
	* valeur à passer à validerRGInterroNbreJours05.<br/>
	*/
	public static void setValiderRGInterroNbreJours05(
			final Boolean pValiderRGInterroNbreJours05) {	
		validerRGInterroNbreJours05 = pValiderRGInterroNbreJours05;
	} // Fin de setValiderRGInterroNbreJours05(...)._______________________


	
	/**
	 * method getValiderRGInterroNbreJours06() :<br/>
	 * Getter du Boolean activant la RG_INTERRO_NBREJOURS_06 : 
	 * "le nombre de jours doit être inférieur ou égal à 10".<br/>
	 * <br/>
	 *
	 * @return validerRGInterroNbreJours06 : Boolean.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public static Boolean getValiderRGInterroNbreJours06() 
			throws MalformedURLException {
	
		if (validerRGInterroNbreJours06 == null) {
			lireControleNbreJoursActif();
		}
		
		return validerRGInterroNbreJours06;
		
	} // Fin de getValiderRGInterroNbreJours06().__________________________


	
	/**
	* method setValiderRGInterroNbreJours06(
	* Boolean pValiderRGInterroNbreJours06) :<br/>
	* Setter du Boolean activant la RG_INTERRO_NBREJOURS_06 : 
	* "le nombre de jours doit être inférieur ou égal à 10".<br/>
	* <br/>
	*
	* @param pValiderRGInterroNbreJours06 : Boolean : 
	* valeur à passer à validerRGInterroNbreJours06.<br/>
	*/
	public static void setValiderRGInterroNbreJours06(
			final Boolean pValiderRGInterroNbreJours06) {	
		validerRGInterroNbreJours06 = pValiderRGInterroNbreJours06;
	} // Fin de setValiderRGInterroNbreJours06(...)._______________________



	
	
} // FIN DE LA CLASSE GestionnaireRGInterrogationMeteo.----------------------
