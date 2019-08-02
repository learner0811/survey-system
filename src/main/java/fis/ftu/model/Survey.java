package fis.ftu.model;
// Generated Jul 13, 2019 1:15:12 AM by Hibernate Tools 5.2.10.Final

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * Survey generated by hbm2java
 */
@Entity(name ="survey")
@Table(name = "survey", catalog = "survey")
public class Survey implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idsurvey;
	private String name;
	private String description;
	private Date datecreate;
	private Category category;		
	private List<Question> questions = new ArrayList<Question>(0);

	public Survey() {
	}

	public Survey(int idsurvey) {
		this.idsurvey = idsurvey;
	}

	public Survey(int idsurvey, String name, String description, Date datecreate, List<AnswerSheet> answerSheets,
			List<Question> questions) {
		this.idsurvey = idsurvey;
		this.name = name;
		this.description = description;
		this.datecreate = datecreate;		
		this.questions = questions;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idsurvey", unique = true, nullable = false)
	public int getIdsurvey() {
		return this.idsurvey;
	}

	public void setIdsurvey(int idsurvey) {
		this.idsurvey = idsurvey;
	}

	@Column(name = "name", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datecreate", length = 19)
	public Date getDatecreate() {
		return this.datecreate;
	}

	public void setDatecreate(Date datecreate) {
		this.datecreate = datecreate;
	}

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "survey")
	public List<AnswerSheet> getAnswerSheets() {
		return this.answerSheets;
	}

	public void setAnswerSheets(List<AnswerSheet> answerSheets) {
		this.answerSheets = answerSheets;
	}*/

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)		
	@JoinColumn(name = "idsurvey", nullable = false)
	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idcategory", nullable = false)	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}	
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Question))
			return false;
		Survey survey = (Survey) o;
		return Objects.equals(idsurvey, survey.idsurvey);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idsurvey);
	}
}
