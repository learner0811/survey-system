package fis.ftu.model;
// Generated Jul 13, 2019 1:15:12 AM by Hibernate Tools 5.2.10.Final

import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Question generated by hbm2java
 */
@Entity
@Table(name = "question", catalog = "survey")
public class Question implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idquestion;
	private String name;
	private Integer order;
	private String type;
	private List<Choice> choices = new ArrayList<Choice>(0);

	public Question() {
	}

	public Question(int idquestion, Survey survey) {
		this.idquestion = idquestion;
	}

	public Question(int idquestion, Survey survey, String name, Integer order, List<Answer> answers,
			List<Choice> choices) {
		this.idquestion = idquestion;
		this.name = name;
		this.order = order;
		this.choices = choices;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idquestion", unique = true, nullable = false)
	public int getIdquestion() {
		return this.idquestion;
	}

	public void setIdquestion(int idquestion) {
		this.idquestion = idquestion;
	}

	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "idsurvey", nullable = false) public Survey getSurvey() {
	 * return this.survey; }
	 * 
	 * public void setSurvey(Survey survey) { this.survey = survey; }
	 */

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "orderno")
	public Integer getOrder() {
		return this.order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/*
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "question") public List<Answer>
	 * getAnswers() { return this.answers; }
	 * 
	 * public void setAnswers(List<Answer> answers) { this.answers = answers; }
	 */

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "idquestion", nullable = false)	
	public List<Choice> getChoices() {
		return this.choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Question))
			return false;
		Question question = (Question) o;
		return Objects.equals(idquestion, question.idquestion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idquestion);
	}
}
