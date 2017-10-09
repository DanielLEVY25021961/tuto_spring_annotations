package levy.daniel.application.model.metier.user;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;

/**
 * class User :<br/>
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
 * @since 25 sept. 2017
 *
 */
public class User 
	implements Serializable, Comparable<User>, Cloneable
		, IExportateurCsv, IExportateurJTable {

	// ************************ATTRIBUTS************************************/

	/**
	 * serialVersionUID : long :<br/>
	 * .<br/>
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * POINT_VIRGULE : char :<br/>
	 * ';'.<br/>
	 */
	public static final char POINT_VIRGULE = ';';
	
	
	/**
	 * id : Long :<br/>
	 * ID en base.<br/>
	 */
	private Long id;

	
	/**
	 * login : String :<br/>
	 * login.<br/>
	 */
	private String login;

	
	/**
	 * mdp : String :<br/>
	 * Mot de passe.<br/>
	 */
	private String mdp;

	
	/**
	 * profil : String :<br/>
	 * Profil du User (administrateur, modérateur, ...).<br/>
	 */
	private String profil;
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(User.class);


	// *************************METHODES************************************/
	

	
	 /**
	 * method CONSTRUCTEUR User() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public User() {
		this(null, null, null, null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * method CONSTRUCTEUR User() :<br/>
	 * CONSTRUCTEUR COMPLET.<br/>
	 * SANS ID en Base.<br/>
	 * <br/>
	 *
	 *@param pLogin : String : Login.<br/>
	 * @param pMdp : String : Mot de passe.<br/>
	 * @param pProfil : String : Profil du User 
	 * (administrateur, modérateur, ...).<br/>
	 */
	public User(final String pLogin, final String pMdp
				, final String pProfil) {
		this(null, pLogin, pMdp, pProfil);
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	
	
	
	 /**
	 * method CONSTRUCTEUR User(CONSTRUCTEUR COMPLET BASE) :<br/>
	 * CONSTRUCTEUR COMPLET BASE.<br/>
	 * AVEC ID EN BASE.<br/>
	 * <br/>
	 *
	 * @param pId : Long : ID en base.<br/>
	 * @param pLogin : String : Login.<br/>
	 * @param pMdp : String : Mot de passe.<br/>
	 * @param pProfil : String : Profil du User 
	 * (administrateur, modérateur, ...).<br/>
	 */
	public User(
			final Long pId
			, final String pLogin, final String pMdp
				, final String pProfil) {
		
		super();
		
		this.id = pId;
		this.login = pLogin;
		this.mdp = pMdp;
		this.profil = pProfil;
		
	} // Fin de CONSTRUCTEUR COMPLET BASE._________________________________


	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {

		final int prime = 31;
		
		int result = 1;
		
		result = prime * result 
				+ ((this.login == null) ? 0 : this.login.hashCode());
		result = prime * result 
				+ ((this.mdp == null) ? 0 : this.mdp.hashCode());
		
		return result;
		
	} // Fin de hashCode().________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean equals(
			final Object pObj) {

		if (this == pObj) {
			return true;
		}
		
		if (pObj == null) {
			return false;
		}
		
		if (!(pObj instanceof User)) {
			return false;
		}
		
		final User other = (User) pObj;
		
		/* login. */
		if (this.login == null) {
			if (other.login != null) {
				return false;
			}
		}
		else if (!this.login.equals(other.login)) {
			return false;
		}
		
		/* mdp. */
		if (this.mdp == null) {
			if (other.mdp != null) {
				return false;
			}
		}
		else if (!this.mdp.equals(other.mdp)) {
			return false;
		}
		
		return true;
		
	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int compareTo(
			final User pUser) {

		if (this == pUser) {
			return 0;
		}

		if (pUser == null) {
			return -1;
		}

		int compareLogin = 0;
		int compareMdp = 0;
		
		/* login. */
		if (this.getLogin() == null) {
			if (pUser.getLogin() != null) {
				return +1;
			}
		} else {
			if (pUser.getLogin() == null) {
				return -1;
			}
		}
		
		compareLogin 
			= this.getLogin().compareToIgnoreCase(pUser.getLogin());
		
		if (compareLogin != 0) {
			return compareLogin;
		}

		/* Mdp. */
		if (this.getMdp() == null) {
			if (pUser.getMdp() != null) {
				return +1;
			}
			return 0;
		} 
		
		if (pUser.getMdp() == null) {
			return -1;
		}
		
		compareMdp 
			= this.getMdp().compareToIgnoreCase(pUser.getMdp());
		
		return compareMdp;
		
	} // Fin de compareTo(...).____________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final User clone() throws CloneNotSupportedException {
		
		final User userClone = (User) super.clone();
		
		userClone.setId(this.getId());
		userClone.setLogin(this.getLogin());
		userClone.setMdp(this.getMdp());
		userClone.setProfil(this.getProfil());
		
		return userClone;
		
	} // Fin de clone().___________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {

		final StringBuilder builder = new StringBuilder();
		
		builder.append("User [");
		
		if (this.id != null) {
			builder.append("id=");
			builder.append(this.id);
			builder.append(", ");
		}
		if (this.login != null) {
			builder.append("login=");
			builder.append(this.login);
			builder.append(", ");
		}
		if (this.mdp != null) {
			builder.append("mdp=");
			builder.append(this.mdp);
			builder.append(", ");
		}
		if (this.profil != null) {
			builder.append("profil=");
			builder.append(this.profil);
		}
		
		builder.append(']');
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________



	/**
	 * {@inheritDoc}
	 * <br/>
	 * "id;login;mdp;profil;".<br/>
	 */
	@Override
	public final String getEnTeteCsv() {
		return "id;login;mdp;profil;";
	} // Fin de getEnTeteCsv().____________________________________________



	/**
	 * {@inheritDoc}
	 * "id;login;mdp;profil;".<br/>
	 */
	@Override
	public final String toStringCsv() {
		
		final StringBuilder stb = new StringBuilder();
		
		stb.append(this.getId());
		stb.append(POINT_VIRGULE);
		stb.append(this.getLogin());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMdp());
		stb.append(POINT_VIRGULE);
		stb.append(this.getProfil());
		stb.append(POINT_VIRGULE);
		
		return stb.toString();
		
	} // Fin de toStringCsv()._____________________________________________



	/**
	 * {@inheritDoc}
	 * "id;login;mdp;profil;".<br/>
	 */
	@Override
	public final String getEnTeteColonne(
			final int pI) {
		
		String entete = null;

		switch (pI) {

		case 0:
			entete = "id";
			break;

		case 1:
			entete = "login";
			break;

		case 2:
			entete = "mdp";
			break;

		case 3:
			entete = "profil";
			break;

		default:
			entete = "invalide";
			break;

		} // Fin du Switch._________________________________

		return entete;

	} // Fin de getEnTeteColonne(...)._____________________________________



	/**
	 * {@inheritDoc}
	 * "id;login;mdp;profil;".<br/>
	 */
	@Override
	public final Object getValeurColonne(
			final int pI) {
		
		Object valeur = null;

		switch (pI) {

		case 0:
			valeur = this.getId();
			break;

		case 1:
			valeur = this.getLogin();
			break;

		case 2:
			valeur = this.getMdp();
			break;

		case 3:
			valeur = this.getProfil();
			break;

		default:
			valeur = "invalide";
			break;

		} // Fin du Switch._________________________________

		return valeur;

	} // Fin de getValeurColonne(...)._____________________________________


	
	/**
	 * method getId() :<br/>
	 * Getter de l'ID en base.<br/>
	 * <br/>
	 *
	 * @return id : Long.<br/>
	 */
	public Long getId() {	
		return this.id;
	} // Fin de getId().___________________________________________________



	/**
	* method setId(
	* Long pId) :<br/>
	* Setter de l'ID en base.<br/>
	* <br/>
	*
	* @param pId : Long : valeur à passer à id.<br/>
	*/
	public void setId(
			final Long pId) {	
		this.id = pId;
	} // Fin de setId(...).________________________________________________


	
	/**
	 * method getLogin() :<br/>
	 * Getter du Login.<br/>
	 * <br/>
	 *
	 * @return login : String.<br/>
	 */
	public String getLogin() {	
		return this.login;
	} // Fin de getLogin().________________________________________________


	
	/**
	* method setLogin(
	* String pLogin) :<br/>
	* Setter du Login.<br/>
	* <br/>
	*
	* @param pLogin : String : valeur à passer à login.<br/>
	*/
	public void setLogin(
			final String pLogin) {	
		this.login = pLogin;
	} // Fin de setLogin(...)._____________________________________________


	
	/**
	 * method getMdp() :<br/>
	 * Getter du Mot de passe.<br/>
	 * <br/>
	 *
	 * @return mdp : String.<br/>
	 */
	public String getMdp() {	
		return this.mdp;
	} // Fin de getMdp().__________________________________________________


	
	/**
	* method setMdp(
	* String pMdp) :<br/>
	* Setter du Mot de passe.<br/>
	* <br/>
	*
	* @param pMdp : String : valeur à passer à mdp.<br/>
	*/
	public void setMdp(
			final String pMdp) {	
		this.mdp = pMdp;
	} // Fin de setMdp(...)._______________________________________________


	
	/**
	 * method getProfil() :<br/>
	 * Getter du Profil du User (administrateur, modérateur, ...).<br/>
	 * <br/>
	 *
	 * @return profil : String.<br/>
	 */
	public String getProfil() {	
		return this.profil;
	} // Fin de getProfil()._______________________________________________


	
	/**
	* method setProfil(
	* String pProfil) :<br/>
	* Setter du Profil du User (administrateur, modérateur, ...).<br/>
	* <br/>
	*
	* @param pProfil : String : valeur à passer à profil.<br/>
	*/
	public void setProfil(
			final String pProfil) {	
		this.profil = pProfil;
	} // Fin de setProfil(...).____________________________________________

	
	
} // FIN DE LA CLASSE User.--------------------------------------------------
