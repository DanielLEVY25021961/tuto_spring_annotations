package levy.daniel.application.model.services.metier;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.LigneRG;
import levy.daniel.application.model.services.valideurs.IValideurGeneric;
import levy.daniel.application.model.services.valideurs.LigneRapportValidation;

/**
 * class AbstractServiceGeneric :<br/>
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
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @param <T> : Type paramétré : Classe réelle d'un Objet métier.<br/>
 * @since 26 août 2017
 *
 */
public abstract class AbstractServiceGeneric<T> implements IServiceGeneric<T> {

	// ************************ATTRIBUTS************************************/

		
	/**
	 * listeRGImplementees : List&lt;LigneRG&gt; :<br/>
	 * Liste des RG implémentées dans le Service.<br/>
	 * Une LigneRG encapsule :<br/>
	 * <br/>
	 * [id;Actif;activité des contrôles sur l'attribut;activité de la RG
	 * ;RG implémentée;clé du type de contrôle;type de contrôle
	 * ;Message d'erreur;Objet Métier concerné;Attribut concerné
	 * ;Classe implémentant la RG;Méthode implémentant la RG;].<br/>
	 * <br/>
	 */
	protected transient List<LigneRG> listeRGImplementees 
		= new ArrayList<LigneRG>();

	
	/**
	 * objetMetier : T :<br/>
	 * Objet métier géré par le présent service.<br/>
	 */
	protected transient T objetMetier;

	
	/**
	 * valideur : IValideurGeneric<T> :<br/>
	 * Valideur dédié à la validation de l'objet métier 
	 * de type paramétré T traité par ce service.<br/>
	 */
	protected transient IValideurGeneric<T> valideur;
	
	
		
	/**
	 * erreurs : Map<String,Map<String,String>> :<br/>
	 * map&lt;String, map&lt;String, String&gt;&gt; 
	 * contenant les messages d'erreur avec :
	 * <ul>
	 * <li>String : le nom de l'attribut de l'Objet métier concerné 
	 * par le message d'erreur (par exemple "prénom").</li>
	 * <li>map&lt;String, String&gt; : une map contenant :
	 * <ul>
	 * <li>String : le nom de la RG violée (par exemple 
	 * "RG_CLIENT_NOM_01 : le nom du client doit être renseigné.").</li>
	 * <li>String : le message d'erreur relatif à la RG 
	 * (par exemple : "Le nom n'est pas renseigné (obligatoire).").</li>
	 * </ul>
	 * </ul>
	 * </ul>
	 * this.erreurs n'est jamais null. 
	 * Elle est vide si il n'y a aucune erreur.<br/>
	 */
	protected transient Map<String, Map<String, String>> erreurs 
			= new ConcurrentHashMap<String, Map<String, String>>();
	
	
	/**
	 * controles : Map<String,Map<String,LigneRapportValidation>> :<br/>
	 * map&lt;String, map&lt;String, LigneRapportValidation&gt;&gt; 
	 * contenant les rapports de contrôle (validation) avec :
	 * <ul>
	 * <li>String : le nom de l'attribut de l'Objet métier concerné 
	 * par le rapport de contrôle (par exemple "prénom").</li>
	 * <li>map&lt;String, LigneRapportValidation&gt; : une map contenant :
	 * <ul>
	 * <li>String : le nom de la RG violée (par exemple 
	 * "RG_CLIENT_NOM_01 : le nom du client doit être renseigné.").</li>
	 * <li>LigneRapportValidation : Pure fabrication encapsulant 
	 * tous les éléments d'information relatifs à un contrôle 
	 * (validation d'une RG).</li>
	 * <br/>
	 * Une LigneRapportValidation encapsule : <br/>
	 * "id;nom de la RG;Résultat de la Validation;
	 * Message d'Erreur du contrôle;Actif;
	 * activité des contrôles sur l'attribut;activité de la RG;
	 * clé du type de contrôle;type de contrôle;Message d'erreur;
	 * Objet Métier concerné;Attribut concerné;
	 * Classe implémentant la RG;Méthode implémentant la RG;".<br/>
	 * </ul>
	 * </ul>
	 * </ul>
	 * this.controles n'est jamais null. 
	 * Elle est vide si il n'y a aucun contrôle effectué 
	 * dans l'application.<br/>
	 */
	protected transient Map<String, Map<String, LigneRapportValidation>> controles 
	= new ConcurrentHashMap<String, Map<String, LigneRapportValidation>>();

	
	/**
	 * controlesList : List&lt;LigneRapportValidation&gt; :<br/>
	 * Liste des Pures fabrications encapsulant 
	 * tous les éléments d'information relatifs à un contrôle 
	 * (validation d'une RG).</li>
	 * <br/>
	 * Une LigneRapportValidation encapsule : <br/>
	 * "id;nom de la RG;Résultat de la Validation;
	 * Message d'Erreur du contrôle;Actif;
	 * activité des contrôles sur l'attribut;activité de la RG;
	 * clé du type de contrôle;type de contrôle;Message d'erreur;
	 * Objet Métier concerné;Attribut concerné;
	 * Classe implémentant la RG;Méthode implémentant la RG;".<br/>
	 * </ul>
	 * </ul>
	 * </ul>
	 */
	protected transient List<LigneRapportValidation> controlesList 
		= new ArrayList<LigneRapportValidation>();

	
	/**
	 * valide : Boolean :<br/>
	 * Boolean qui stipule si le contrôle effectué par le valideur 
	 * est sans erreur ou pas.<br/>
	 */
	protected transient Boolean valide;
	
	
	/**
	 * SAUT_LIGNE : String :<br/>
	 * "\n".<br/>
	 */
	public static final String SAUT_LIGNE = "\n";
	

	/**
	 * SAUT_LIGNE_HTML : String :<br/>
	 * "<br/>".<br/>
	 */
	public static final String SAUT_LIGNE_HTML = "<br/>";
	
	
	/**
	 * POINT_VIRGULE : char :<br/>
	 * ';'.<br/>
	 */
	public static final char POINT_VIRGULE = ';';

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(AbstractServiceGeneric.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR AbstractServiceGeneric() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractServiceGeneric() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	
	/* CREATE ************/


	/**
	 * {@inheritDoc}
	 */
	@Override
	public final T create(
			final T pObject) {
		
		return null;
		
	} // Fin de create(...)._______________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Long createReturnId(
			final T pObject) {
		
		return null;
		
	} // Fin de createReturnId(...)._______________________________________

	
	
	/* READ *************/

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final T retrieve(
			final T pObject) {
		
		return null;
		
	} // Fin de retrieve(...)._____________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final T getOne(
			final Long pId) {
		
		return null;
		
	} // Fin de getOne(...)._______________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<T> findAll() {
		
		return null;
		
	} // Fin de findAll()._________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<T> findAllMax(
			final Long pMax) {
		
		return null;
		
	} // Fin de findAllMax(...).___________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Iterable<T> findAll(
			final Iterable<Long> pIds) {
		
		return null;
		
	} // Fin de findAll(...).______________________________________________
	
	
	

	/* UPDATE *************/

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final T update(
			final T pObject) {
		
		return null;
		
	} // Fin de update(...)._______________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final <S extends T> S save(
			final S pObject) {
		
		return null;
		
	} // Fin de save(...)._________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final <S extends T> Iterable<S> save(
			final Iterable<S> pObjects) {
		
		return null;
		
	} // Fin de save(...)._________________________________________________
	
	
	

	/* DELETE *************/
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean delete(
			final T pObject) {
		
		return false;
		
	} // Fin de delete(...)._______________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void delete(
			final Long pId) {
		
		/***/
		
	} // Fin de delete(...)._______________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean deleteBoolean(
			final Long pId) {
		
		return false;
		
	} // Fin de deleteBoolean(...).________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void deleteAll() {
		
		/***/
		
	} // Fin de deleteAll()._______________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void delete(
			final Iterable<? extends T> pObjects) {
		
		/****/
		
	} // Fin de delete(...)._______________________________________________

	
	
	/* TOOLS *************/

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean exists(
			final Long pId) {
		
		return false;
		
	} // Fin de exists(...)._______________________________________________

	
	
	/**
	 * method count() :<br/>
	 * Returns the number of entities available.<br/>
	 * <br/>
	 *
	 * @return : Long : the number of entities.<br/>
	 */
	@Override
	public final Long count() {
		
		return null;
		
	} // Fin de count().___________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirNomObjetMetier() {
		return this.objetMetier.getClass().getSimpleName();
	} // Fin de fournirNomObjetMetier().___________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract String founirNomClasse();
	
	


	/* VALIDATION ************/
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Map<String, Map<String, String>> validate(
			final T pObject) 
					throws MalformedURLException {
		
		/* DELEGUE la validation des RG à 'this.valideur'. */
		this.valideur.validate(pObject);
		
		/* Alimente 'this.erreurs'. */
		this.erreurs.putAll(this.valideur.getErreurs());
		
		/* Alimente 'this.Controle'. */
		this.controles.putAll(this.valideur.getControles());
		
		/* Alimente 'this.listeRGImplementees'. */
		this.listeRGImplementees.addAll(
				this.valideur.getListeRGImplementees());
		
		/* Alimente 'this.controlesList'. */
		this.controlesList.addAll(this.valideur.getControlesList());
		
		/* Alimente 'this.valide'. */
		this.valide = this.valideur.getValide();
		
		/* Retourne 'this.erreurs'. */
		return this.erreurs;
		
	} // Fin de validate(...)._____________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnTeteCsvRGImplementees() {
		
		/* DELEGUE à 'this.valideur'. */
		return this.valideur.fournirEnTeteCsvRGImplementees();
		
	} // Fin de fournirEnTeteCsvRGImplementees().__________________________
	

		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirStringListeCsvRGImplementees() {
		
		/* DELEGUE à 'this.valideur'. */
		return this.valideur.fournirStringListeCsvRGImplementees();
		
	} // Fin de fournirStringListeCsvRGImplementees()._____________________


		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirStringListeCsvRGImplementeesAvecEntete() {
		
		/* DELEGUE à 'this.valideur'. */
		return this.valideur
				.fournirStringListeCsvRGImplementeesAvecEntete();
		
	} // Fin de fournirStringListeCsvRGImplementeesAvecEntete().___________


			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Map<String, String> fournirMapErreursAttribut(
			final String pAttribut) {
		
		/* DELEGUE à 'this.valideur'. */
		return this.valideur.fournirMapErreursAttribut(pAttribut);
		
	} // Fin de fournirMapErreursAttribut(...).____________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirStringErreursAttribut(
			final String pAttribut
				, final String pSautLigne) {
		
		/* DELEGUE à 'this.valideur'. */
		return this.valideur
				.fournirStringErreursAttribut(pAttribut, pSautLigne);
		
	} // Fin de fournirStringErreursAttribut(...)._________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Collection<String> fournirListeMessagesErreurs(
			final Map<String, String> pMap) {
		
		/* DELEGUE à 'this.valideur'. */
		return this.valideur.fournirListeMessagesErreurs(pMap);
		
	} // Fin de fournirListeMessagesErreurs(...).__________________________
	
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherErreurs() {
		
		/* DELEGUE à 'this.valideur'. */
		return this.valideur.afficherErreurs();
		
	} // Fin de afficherErreurs()._________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Map<String, LigneRapportValidation> 
				fournirMapControlesAttribut(
							final String pAttribut) {
		
		/* DELEGUE à 'this.valideur'. */
		return this.valideur.fournirMapControlesAttribut(pAttribut);
		
	} // Fin de fournirMapControlesAttribut(...).__________________________

	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirStringControlesAttribut(
			final String pAttribut
				, final String pSautLigne) {
		
		/* DELEGUE à 'this.valideur'. */
		return this.valideur
				.fournirStringControlesAttribut(pAttribut, pSautLigne);
		
	} // Fin de fournirStringControlesAttribut(...)._______________________

	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Collection<LigneRapportValidation> 
			fournirListeMessagesControles(
					final Map<String, LigneRapportValidation> pMap) {
		
		/* DELEGUE à 'this.valideur'. */
		return this.valideur.fournirListeMessagesControles(pMap);
		
	} // Fin de fournirListeMessagesControles(...).________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherControles() {
		
		/* DELEGUE à 'this.valideur'. */
		return this.valideur.afficherControles();
		
	} // Fin de afficherControles()._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IValideurGeneric<T> getValideur() {
		return this.valideur;
	} // Fin de getValideur()._____________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<LigneRG> getListeRGImplementees() {
		return this.listeRGImplementees;
	} // Fin de getListeRGImplementees().__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Map<String, Map<String, String>> getErreurs() {
		return this.erreurs;
	} // Fin de getErreurs().______________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Map<String, Map<String, LigneRapportValidation>> getControles() {
		return this.controles;
	} // Fin de getControles().____________________________________________


		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<LigneRapportValidation> getControlesList() {
		return this.controlesList;
	} // Fin de getControlesList().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Boolean getValide() {
		return this.valide;
	} // Fin de getValide()._______________________________________________

		
	
} // FIN DE LA CLASSE AbstractServiceGeneric.--------------------------------
