package levy.daniel.application.model.services.metier;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.LigneRG;
import levy.daniel.application.model.services.valideurs.IValideurGeneric;
import levy.daniel.application.model.services.valideurs.LigneRapportValidation;

/**
 * class IServiceGeneric :<br/>
 * .<br/>
 * <br/>
 * - Exemple d'utilisation :<br/>
 * <br/>
 * - Mots-clé :<br/>
 * Généricité, Type paramétré, <br/>
 * <br/>
 * - Dépendances :<br/>
 * <br/>
 *
 * @author dan Lévy
 * @version 1.0
 * @param <T> : Type paramétré : Classe réelle d'un Objet métier.<br/>
 * @since 25 août 2017
 */
public interface IServiceGeneric<T> {

	
	/* CREATE ************/

	/**
	 * method create(
	 * T pObject) :<br/>
	 * <ul>
	 * <li>Crée un Objet metier de type paramétré T pObject en base.</li>
	 * <li>retourne l'Objet métier de type paramétré T créé en base.</li>
	 * </ul>
	 *
	 * @param pObject : T : Objet métier de type paramétré T.<br/>
	 * 
	 * @return : T : Objet métier de type paramétré T créé en base.<br/>
	 */
	T create(T pObject);

	
	
	/**
	 * method createReturnId(
	 * T pObject) :<br/>
	 * <ul>
	 * <li>Crée un Objet metier de type paramétré T pObject.</li>
	 * <li>retourne l'ID de l'Objet métier de type paramétré T créé.</li>
	 * </ul>
	 *
	 * @param pObject : T : Objet métier de type paramétré T.<br/>
	 * 
	 * @return : T : Objet métier de type paramétré T créé.<br/>
	 */
	Long createReturnId(T pObject);

	
	
	/* READ *************/

	/**
	 * method retrieve(
	 * T pObject) :<br/>
	 * <ul>
	 * <li>Recherche un objet métier de Type 
	 * paramétré T pObject en base.</li>
	 * <li>Retourne l'objet métier de Type 
	 * paramétré T pObject trouvé en base.</li>
	 * </ul>>
	 *
	 * @param pObject : T : objet métier de Type paramétré T.<br/>
	 * 
	 * @return : T : objet métier de Type paramétré T 
	 * existant en base.<br/>
	 */
	T retrieve(T pObject);
	
	

	/**
	 * method getOne(
	 * Long pId) :<br/>
	 * <ul>
	 * <li>Recherche un Objet métier de Type 
	 * paramétré T via son ID en base.</li>
	 * <li>Retourne l'objet métier de Type 
	 * paramétré T pObject trouvé en base.</li>
	 * <li>Compatible avec SpringData.</li>
	 * </ul>
	 *
	 * @param pId : Long : ID en base de l'Objet métier.
	 * 
	 * @return : T : Objet métierde Type paramétré T 
	 * existant en base.<br/>
	 */
	T getOne(Long pId);
	
	

	/**
	 * method findAll() :<br/>
	 * Retourne la liste de tous les objets métier de Type paramétré T 
	 * présents en base.<br/>
	 * <br/>
	 *
	 * @return : List&lt;T&gt; : 
	 * liste de tous les objets métier de Type paramétré T 
	 * présents en base.<br/>
	 */
	List<T> findAll();
	
	

	/**
	 * method findAll(
	 * Long pMax) :<br/>
	 * Retourne la liste des pMax premiers objets métier 
	 * de Type paramétré T présents en base.<br/>
	 * <br/>
	 * 
	 * @param pMax : Long : Nombre maximal d'objets métier 
	 * à remonter de la base.<br/>
	 *
	 * @return : List&lt;T&gt; : liste des pMax premiers objets métier 
	 * de Type paramétré T présents en base.<br/>
	 */
	List<T> findAllMax(Long pMax);
	
	

	/**
	 * method findAll(
	 * Iterable&lt;ID&gt; pIds) :<br/>
	 * Returns all instances of the type T with the given IDs.<br/>
	 * <br/>
	 *
	 * @param pIds : Iterable&lt;ID&gt;.<br/>
	 * 
	 * @return Iterable&lt;T&gt;.<br/>
	 */
	Iterable<T> findAll(
			Iterable<Long> pIds);
	
	
	

	/* UPDATE *************/

	
	/**
	 * method update(
	 * T pObject) :<br/>
	 * <ul>
	 * <li>Modifie un objet métier de Type paramétré T pObject 
	 * existant en base.</li>
	 * <li>Retourne l'objet métier de Type paramétré T 
	 * pObject modifié en base</li>
	 * </ul>
	 *
	 * @param pObject : T : objet métier de Type paramétré T.<br/>
	 * 
	 * @return : T : objet métier de Type paramétré 
	 * T modifié en base.<br/>
	 */
	T update(T pObject);
	
	

	/**
	 * method save(
	 * S pObject) :<br/>
	 * <ul>
	 * <li>Sauvegarde l'objet métier pObject de type paramétré S 
	 * (S pouvant être n'importe quelle sous-classe de T) 
	 * en base.</li>
	 * <li>Retourne l'instance sauvegardée en base.</li>
	 * </ul>
	 *
	 * @param pObject : S : objet métier de Type paramétré S 
	 * où S est une sous-classe de T.<br/>
	 * 
	 * @return : S : Objet métier de type paramétré S 
	 * (n'importe quelle sous-classe de T) créé en base.<br/>
	 */
	<S extends T> S save(S pObject);
	
	

	/**
	 * method save(
	 * Iterable&lt;S&gt; pObjects) :<br/>
	 * <ul>
	 * <li>Sauvegarde en base tous les objets métier de type S 
	 * (S pouvant être n'importe quelle sous-classe de T) 
	 * contenus dans la collection itérable d'objets métier 
	 * de type S "pObjects".</li>
	 * <li>Retourne la Collection itérable d'objets de type S 
	 * (sous-classes de T) sauvegardés en base.</li>
	 * </ul>
	 *
	 * @param pObjects : Iterable&lt;S&gt; : 
	 * collection itérable d'objets métier de type S 
	 * (S pouvant être n'importe quelle sous-classe de T).<br/>
	 *  
	 * @return : Iterable&lt;S&gt; : La Collection itérable d'objets 
	 * de type S (sous-classes de T) sauvegardés en base.<br/>
	 * 
	 * @throws IllegalArgumentException in case the given 
	 * entity is {@literal null}.
	 */
	<S extends T> Iterable<S> save(Iterable<S> pObjects);
	
	
	

	/* DELETE *************/
	

	/**
	 * method delete(
	 * T pObject) :<br/>
	 * <ul>
	 * <li>Détruit un un objet métier de Type paramétré T pObject
	 * existant en base.</li>
	 * <li>Retourne un boolean (true si OK) précisant 
	 * si l'opération de destruction a eu lieu.</li>
	 * </ul>
	 *
	 * @param pObject : T : objet métier de Type paramétré T.<br/>
	 * 
	 * @return : boolean : true si l'objet métier de Type paramétré T 
	 * a été détruit en base.<br/>
	 */
	boolean delete(T pObject);

	
	
	/**
	 * method delete(
	 * Long pId) :<br/>
	 * Détruit un un objet métier de Type paramétré T 
	 * existant en base via son ID.<br/>
	 * <br/>
	 *
	 * @param pId : Long : ID en base.<br/>
	 */
	void delete(Long pId);
	

	
	/**
	 * method deleteBoolean(
	 * Long pId) :<br/>
	 * <ul>
	 * <li>Détruit un un objet métier de Type paramétré T 
	 * existant en base via son ID en base.</li>
	 * <li>Retourne un boolean (true si OK) précisant 
	 * si l'opération de destruction a eu lieu.</li>
	 * </ul>
	 *
	 * @param pId : Long : ID en base.<br/>
	 * 
	 * @return boolean : true si l'objet d'ID pId 
	 * a été détruit en base.<br:>
	 */
	boolean deleteBoolean(Long pId);
	
	

	/**
	 * method deleteAll() :<br/>
	 * Deletes all entities managed by the repository.<br/>
	 * <br/>
	 */
	void deleteAll();
	

	
	/**
	 * method delete(
	 * Iterable&lt;? extends T&gt; entities) :<br/>
	 * Détruit en base tous les objets métier de type T 
	 * (ou de n'importe quelle sous-classe de T) 
	 * contenus dans la collection itérable pObjects.<br/>
	 * <br/>
	 *
	 * @param pObjects : Iterable&lt;? extends T&gt; : 
	 * collection itérable d'objets de type T ou sous-classes de T.<br/>
	 * 
	 * @throws IllegalArgumentException in case 
	 * the given {@link Iterable} is {@literal null}.<br/>
	 */
	void delete(Iterable<? extends T> pObjects);

	
	
	/* TOOLS *************/


	/**
	 * method exists(
	 * ID pId) :<br/>
	 * Returns whether an entity with the given id exists.<br/>
	 * <br/>
	 *
	 * @param pId : ID : must not be {@literal null}.
	 * 
	 * @return true if an entity with the given id exists
	 * , {@literal false} otherwise.<br/>
	 * 
	 * @throws IllegalArgumentException 
	 * if {@code id} is {@literal null}
	 */
	boolean exists(Long pId);

	
	
	/**
	 * method count() :<br/>
	 * Returns the number of entities available.<br/>
	 * <br/>
	 *
	 * @return : Long : the number of entities.<br/>
	 */
	Long count();

	

	/**
	 * method fournirNomObjetMetier() :<br/>
	 * Retourne le nom de la classe de l'objet métier géré par la présente classe de service.<br/>
	 * <br/>
	 * Par exemple : "Classe Client".<br/>
	 * <br/>
	 *
	 * @return : String : Nom de l'objet métier géré par la présente classe de service.<br/>
	 */
	String fournirNomObjetMetier();

	
	
	/**
	 * method founirNomClasse() :<br/>
	 * Retourne le nom de la présente classe de service.<br/>
	 * <br/>
	 * Par exemple : "Classe ClientService".<br/>
	 *
	 * @return : String : nom de la présente classe de service.<br/>
	 */
	String founirNomClasse();

	


	/* VALIDATION ************/
	
	
	/**
	 * method validate( T pObject) :<br/>
	 * <ul>
	 * <li>VALIDE l'Objet métier de type paramétré T pObject.</li>
	 * <li>DELEGUE les tâches de validation des RG 
	 * au IValideurGeneric&lt;T&gt; <b>'this.valideur'</b>.</li> 
	 * <li>RETOURNE la 
	 * map&lt;String, map&lt;String, String&gt;&gt; 
	 * <b>'this.erreurs'</b> contenant les messages d'erreur
	 * avec :
	 * <ul>
	 * <li>String : le nom de l'attribut de l'Objet métier 
	 * concerné par le message d'erreur.</li>
	 * <li>map&lt;String, String&gt; : une map contenant 
	 * le nom de la RG violée ainsi que le message d'erreur
	 * relatif à la RG.</li>
	 * </ul>
	 * <li>ALIMENTE la map&lt;String, map&lt;String
	 * , String&gt;&gt; <b>'this.erreurs'</b>.</li>
	 * <li>
	 * ALIMENTE la map&lt;String, map&lt;String
	 * , LigneRapportValidation&gt;&gt; <b>'this.controles'</b>
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
	 * </li>
	 * <li>ALIMENTE <b>'this.listeRGImplementees'</b>.</li>
	 * <li>ALIMENTE <b>'this.controlesList'</b>.</li>
	 * <li>ALIMENTE le Boolean <b>'this.valide'</b>. Ce Boolean stipule si le contrôle est sans erreur (true) ou pas.</li>
	 * </ul>
	 * <br/>
	 *
	 * @param pObject : T : Objet métier de type paramétré T.<br/>
	 * 
	 * @return : map&lt;String, map&lt;String, String&gt;&gt; : 
	 * Map contenant les attributs de l'Objet métier violant des RG 
	 * ainsi que les messages d'erreur relatifs aux RG violées.<br/>
	 * 
	 * @throws MalformedURLException
	 */
	Map<String, Map<String, String>> validate(T pObject) 
			throws MalformedURLException;

	
	
	/**
	 * method fournirEnTeteCsvRGImplementees() :<br/>
	 * Retourne l'en-Tête Csv pour la liste des règles de gestion 
	 * implémentées dans ce service.<br/>
	 * <br/>
	 * "id;Actif;activité des contrôles sur l'attribut
	 * ;activité de la RG; RG implémentée;clé du type
	 * de contrôle;type de contrôle;Message d'erreur
	 * ;Objet Métier concerné;Attribut concerné;Classe
	 * implémentant la RG; Méthode implémentant la RG;".<br/>
	 * <br/>
	 *
	 * @return : String : en-tête csv des RG.<br/>
	 */
	String fournirEnTeteCsvRGImplementees();

	
	
	/**
	 * method fournirStringListeCsvRGImplementees() :<br/>
	 * <ul>
	 * <li>fournit sous forme de String en csv 
	 * avec séparateur point-virgule ':' 
	 * la liste des règles de gestions
	 * contrôlées dans le présent Service.</li>
	 * <li>Trie les LigneRG en fonction des numéros de RG 
	 * (Comparator).</li>
	 * <li>La liste fournie ne comporte pas d'en-tête.</li>
	 * </ul>
	 * retourne null si this.listeRGImplementees == null.<br/>
	 * <br/>
	 * Une règle de gestion est décomposée comme suit :<br/>
	 * "id;Actif;activité des contrôles sur l'attribut
	 * ;activité de la RG;RG implémentée;clé du type
	 * de contrôle; type de contrôle;Message d'erreur
	 * ;Objet Métier concerné; Attribut
	 * concerné;Classe implémentant la RG
	 * ;Méthode implémentant la RG;properties;clé;".<br/>
	 * <br/>
	 *
	 * @return : String : liste des règles de gestions contrôlées 
	 * dans le présent Service en CSV.<br/>
	 */
	String fournirStringListeCsvRGImplementees();

	
	
	/**
	 * method fournirStringListeCsvRGImplementeesAvecEntete() :<br/>
	 * <ul>
	 * <li>fournit sous forme de String en csv avec séparateur ':'
	 * et saut de ligne après chaque RG 
	 * la liste des règles de gestions (RG) contrôlées 
	 * dans le présent Service.</li>
	 * <li>La liste csv comporte un en-tête.</li>
	 * <li>Utile pour créer un fichier Csv contenant les RG implémentées dans le Valideur.</li>
	 * </ul>
	 * retourne null si this.listeRGImplementees == null.<br/>
	 * <br/>
	 * Une règle de gestion est décomposée comme suit :<br/>
	 * "id;Actif;activité des contrôles sur l'attribut
	 * ;activité de la RG;RG implémentée;clé du type de contrôle;
	 * type de contrôle;Message d'erreur;Objet Métier concerné;
	 * Attribut concerné;Classe implémentant la RG;
	 * Méthode implémentant la RG;properties;clé;".<br/>
	 * <br/>
	 *
	 * @return : String : liste des règles de gestions contrôlées 
	 * dans le présent Service en CSV AVEC en-tête.<br/>
	 */
	String fournirStringListeCsvRGImplementeesAvecEntete();
	

	
	/**
	 * method fournirMapErreursAttribut(
	 * String pAttribut) :<br/>
	 * Fournit les erreurs concernant l'attribut pAttribut 
	 * sous forme de Map&lt;String, String&gt; avec :
	 * <ul>
	 * <li>String : le nom de la RG violée 
	 * (par exemple "RG_CLIENT_NOM_01 : le nom du client 
	 * doit être renseigné.").</li>
	 * <li>String : le message d'erreur relatif à la RG 
	 * (par exemple : "Le nom n'est pas renseigné (obligatoire).").</li>
	 * </ul>
	 * retourne null si pAttribut == null.<br/>
	 * retourne null si this.erreurs == null.<br/>
	 * <br/>
	 *
	 * @param pAttribut : String : Attribut du bean.<br/>
	 * 
	 * @return : Map&lt;String, String&gt; : 
	 * erreurs concernant l'attribut pAttribut.<br/>
	 */
	Map<String, String> fournirMapErreursAttribut(String pAttribut);

	
	
	/**
	 * method fournirStringErreursAttribut(
	 * String pAttribut
	 * , String pSautLigne) :<br/>
	 * <ul>
	 * <li>Retourne seulement les Messages d'erreurs 
	 * relatifs à l'attribut pAttribut concaténés 
	 * dans une unique String.</li>
	 * <li>Chaque message d'erreur est séparé par 
	 * le saut de ligne pSautLigne ("\n" ou "&lt;br/&gt;") 
	 * dans la String résultat.</li>
	 * <li>Utile pour les messages utilisateur dans les IHM.</li>
	 * <li>Par exemple pour l'attribut 'nom' :<br/>
	 * Le nom n'est pas renseigné (obligatoire).<br/>
	 * le nom du client ne doit contenir que des lettres (pas de chiffres).</li>
	 * </ul>
	 * retourne null si pAttribut == null.<br/>
	 * retourne null si this.erreurs == null.<br/>
	 * <br/>
	 *
	 * @param pAttribut : String : Attribut du bean.<br/>
	 * @param pSautLigne : String : "\n" ou "&lt;br/&gt;".<br/>
	 * 
	 * @return : String : Messages d'erreurs concaténés 
	 * dans une unique String.<br/>
	 */
	String fournirStringErreursAttribut(
			String pAttribut
				, String pSautLigne);
	
	
	
	/**
	 * method fournirListeMessagesErreurs(
	 *Map&lt;String, String&gt; pMap) :<br/>
	 * Retourne la Collection des values 
	 * d'une Map&lt;String, String&gt;.<br/>
	 * <br/>
	 * retourne null si pMap == null.<br/>
	 * <br/>
	 *
	 * @param pMap : Map&lt;String, String&gt;.<br/>
	 * 
	 * @return : Collection&lt;String&gt;.<br/>
	 */
	Collection<String> fournirListeMessagesErreurs(
								Map<String, String> pMap);
	

	
	/**
	 * method afficherErreurs() :<br/>
	 * <ul>
	 * <li>Retourne une String pour l'affichage de this.erreurs.</li>
	 * <li>La String retournée contient une ligne par erreur.</li>
	 * <li>n'incorpore pas d'en-tête dans la String.</li>
	 * <li>Chaque ligne est de la forme "attribut;RG;message erreur RG;".</li>
	 * </ul>
	 * Exemple : <br/>
	 * "nom;RG_CLIENT_NOM_01 : 
	 * le nom du client doit être renseigné.;
	 * Le nom n'est pas renseigné (obligatoire)";<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String afficherErreurs();
	

	
	/**
	 * method fournirMapControlesAttribut(
	 * String pAttribut) :<br/>
	 * Fournit les contrôles concernant l'attribut pAttribut 
	 * sous forme de Map&lt;String, LigneRapportValidation&gt; avec :
	 * <ul>
	 * <li>String : le nom de la RG violée 
	 * (par exemple "RG_CLIENT_NOM_01 : le nom du client 
	 * doit être renseigné.").</li>
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
	 * retourne null si pAttribut == null.<br/>
	 * retourne null si this.controles == null.<br/>
	 * <br/>
	 *
	 * @param pAttribut : String : Attribut du bean.<br/>
	 * 
	 * @return : Map&lt;String, LigneRapportValidation&gt; : 
	 * contrôles concernant l'attribut pAttribut.<br/>
	 */
	Map<String, LigneRapportValidation> 
				fournirMapControlesAttribut(String pAttribut);
	

	
	/**
	 * method fournirStringControlesAttribut(
	 * String pAttribut
	 * , String pSautLigne) :<br/>
	 * <ul>
	 * <li>Retourne les Messages de contrôle relatifs à 
	 * l'attribut pAttribut concaténés dans une unique String.</li>
	 * <li>Un message de contrôle est sous la forme 
	 * "nomRG;Résultat du contrôle;message du contrôle;"</li>
	 * <li>Chaque message de contrôle est séparé par 
	 * le saut de ligne pSautLigne ("\n" ou "&lt;br/&gt;") 
	 * dans la String résultat.</li>
	 * <li>Utile pour les rapports de contrôle à l'attention 
	 * de l'utilisateur.</li>
	 * </ul>
	 * Exemple :<br/>
	 * RG_CLIENT_NOM_01 : le nom du client doit être renseigné.;
	 * KO;Le champ nom est null ou vide (que des espaces);<br/>
	 * RG_CLIENT_NOM_02 : le nom du client ne doit contenir 
	 * que des lettres.;OK;Le champ nom est blank (null ou vide). 
	 * Donc pas de chiffres !;<br/>
	 * <br/>
	 * 
	 * retourne null si pAttribut == null.<br/>
	 * retourne null si this.controles == null.<br/>
	 * <br/>
	 *
	 * @param pAttribut : String : Attribut du bean.<br/>
	 * @param pSautLigne : String : "\n" ou "&lt;br&gt;".<br/>
	 * 
	 * @return : String : Messages de contrôle concaténés 
	 * dans une unique String.<br/>
	 */
	String fournirStringControlesAttribut(
			String pAttribut
				, String pSautLigne);
	
	
	
	/**
	 * method fournirListeMessagesControles(
	 * Map&lt;String, LigneRapportValidation&gt; pMap) :<br/>
	 * Retourne la Collection des values 
	 * d'une Map&lt;String, LigneRapportValidation&gt;.<br/>
	 * <br/>
	 * retourne null si pMap == null.<br/>
	 * <br/>
	 *
	 * @param pMap : Map&lt;String, LigneRapportValidation&gt;.<br/>
	 * 
	 * @return : Collection&lt;LigneRapportValidation&gt;.<br/>
	 */
	Collection<LigneRapportValidation> 
			fournirListeMessagesControles(
					Map<String, LigneRapportValidation> pMap);
	

	
	/**
	 * method afficherControles() :<br/>
	 * <ul>
	 * <li>Retourne une String pour l'affichage de this.controles.</li>
	 * <li>La String retournée contient une ligne par contrôle.</li>
	 * <li>Chaque ligne est de la forme 
	 * "attribut;RG;Résultat du contrôle;message du contrôle;".</li>
	 * </ul>
	 * Exemple : <br/>
	 * nom;RG_CLIENT_NOM_01 : le nom du client doit être renseigné.;KO;
	 * Le champ nom est null ou vide (que des espaces);<br/>
	 * nom;RG_CLIENT_NOM_02 : le nom du client ne doit contenir 
	 * que des lettres.;OK;Le champ nom est blank (null ou vide). 
	 * Donc pas de chiffres !;<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String afficherControles();
	

	
	/**
	 * method getValideur() :<br/>
	 * Getter du Valideur dédié à la validation de l'objet métier de type paramétré T traité par ce
	 * service.<br/>
	 * <br/>
	 *
	 * @return valideur : IValideurGeneric<T>.<br/>
	 */
	IValideurGeneric<T> getValideur();

	
	
	/**
	 * method getListeRGImplementees() :<br/>
	 * Getter de la Liste des RG implémentées dans le Service.<br/>
	 * <br/>
	 * Une LigneRG encapsule :<br/>
	 * [id;Actif;activité des contrôles sur l'attribut;activité de la RG ;RG implémentée;clé du type
	 * de contrôle;type de contrôle ;Message d'erreur;Objet Métier concerné;Attribut concerné
	 * ;Classe implémentant la RG;Méthode implémentant la RG;].<br/>
	 * <br/>
	 *
	 * @return listeRGImplementees : List&lt;LigneRG&gt;.<br/>
	 */
	List<LigneRG> getListeRGImplementees();

	
	
	/**
	 * method getErreurs() :<br/>
	 * Getter de la map&lt;String, map&lt;String, String&gt;&gt; contenant les messages d'erreur
	 * avec :
	 * <ul>
	 * <li>String : le nom de l'attribut de l'Objet métier concerné par le message d'erreur (par
	 * exemple "prénom").</li>
	 * <li>map&lt;String, String&gt; : une map contenant :
	 * <ul>
	 * <li>String : le nom de la RG violée (par exemple "RG_CLIENT_NOM_01 : le nom du client doit
	 * être renseigné.").</li>
	 * <li>String : le message d'erreur relatif à la RG (par exemple : "Le nom n'est pas renseigné
	 * (obligatoire).").</li>
	 * </ul>
	 * </ul>
	 * </ul>
	 * this.erreurs n'est jamais null. 
	 * Elle est vide si il n'y a aucune erreur.<br/>
	 * <br/>
	 *
	 * @return erreurs : map&lt;String, map&lt;String, String&gt;&gt;.<br/>
	 */
	Map<String, Map<String, String>> getErreurs();

	
	
	/**
	 * method getControles() :<br/>
	 * Getter de la map&lt;String, map&lt;String , LigneRapportValidation&gt;&gt; contenant les
	 * rapports de contrôle (validation) avec :
	 * <ul>
	 * <li>String : le nom de l'attribut de l'Objet métier concerné par le rapport de contrôle (par
	 * exemple "prénom").</li>
	 * <li>map&lt;String, LigneRapportValidation&gt; : une map contenant :
	 * <ul>
	 * <li>String : le nom de la RG violée (par exemple "RG_CLIENT_NOM_01 : le nom du client doit
	 * être renseigné.").</li>
	 * <li>LigneRapportValidation : Pure fabrication encapsulant tous les éléments d'information
	 * relatifs à un contrôle (validation d'une RG).</li> <br/>
	 * Une LigneRapportValidation encapsule : <br/>
	 * "id;nom de la RG;Résultat de la Validation; Message d'Erreur du contrôle;Actif; activité des
	 * contrôles sur l'attribut;activité de la RG; clé du type de contrôle;type de contrôle;Message
	 * d'erreur; Objet Métier concerné;Attribut concerné; Classe implémentant la RG;Méthode
	 * implémentant la RG;".<br/>
	 * </ul>
	 * </ul>
	 * </ul>
	 * this.controles n'est jamais null. 
	 * Elle est vide si il n'y a aucun contrôle effectué 
	 * dans l'application.<br/>
	 * <br/>
	 *
	 * @return controles : map&lt;String, map&lt;String , LigneRapportValidation&gt;&gt;.<br/>
	 */
	Map<String, Map<String, LigneRapportValidation>> getControles();

	
	
	/**
	 * method getControlesList() :<br/>
	 * Getter de la Liste des Pures fabrications encapsulant tous les éléments d'information
	 * relatifs à un contrôle (validation d'une RG).</li> <br/>
	 * Une LigneRapportValidation encapsule : <br/>
	 * "id;nom de la RG;Résultat de la Validation; Message d'Erreur du contrôle;Actif; activité des
	 * contrôles sur l'attribut;activité de la RG; clé du type de contrôle;type de contrôle;Message
	 * d'erreur; Objet Métier concerné;Attribut concerné; Classe implémentant la RG;Méthode
	 * implémentant la RG;".<br/>
	 * </ul>
	 * </ul>
	 * </ul>
	 * <br/>
	 *
	 * @return controlesList : List&lt;LigneRapportValidation&gt;.<br/>
	 */
	List<LigneRapportValidation> getControlesList();

	
	
	/**
	 * method getValide() :<br/>
	 * Getter du Boolean qui stipule si le contrôle effectué par le validateur est sans erreur ou
	 * pas.<br/>
	 * <br/>
	 *
	 * @return valide : Boolean.<br/>
	 */
	Boolean getValide();

} // FIN DE L'INTERFACE IServiceGeneric<T>.--------------------------------
