package it.ivncr.erp.model.commerciale.contratto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "con_rinnovo_contrattuale")
public class RinnovoContrattuale implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "con_rinnovo_contrattuale_id_seq")
	@SequenceGenerator(name = "con_rinnovo_contrattuale_id_seq", sequenceName = "con_rinnovo_contrattuale_id_seq")
	@Column(name="id")
	private Integer id;

    @ManyToOne
    @JoinColumn(name="contratto_id")
    private Contratto contratto;

	@Column(name="data_decorrenza_pre")
	private Date dataDecorrenzaPre;

	@Column(name="data_termine_pre")
	private Date dataTerminePre;

	@Column(name="data_decorrenza_post")
	private Date dataDecorrenzaPost;

	@Column(name="data_termine_post")
	private Date dataTerminePost;

	@Column(name="note")
	private String note;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("contratto", contratto, false)
			.append("dataDecorrenzaPre", dataDecorrenzaPre)
			.append("dataTerminePre", dataTerminePre)
			.append("dataDecorrenzaPost", dataDecorrenzaPost)
			.append("dataTerminePost", dataTerminePost)
			.append("note", note)
			.toString();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Contratto getContratto() {
		return contratto;
	}

	public void setContratto(Contratto contratto) {
		this.contratto = contratto;
	}

	public Date getDataDecorrenzaPre() {
		return dataDecorrenzaPre;
	}

	public void setDataDecorrenzaPre(Date dataDecorrenzaPre) {
		this.dataDecorrenzaPre = dataDecorrenzaPre;
	}

	public Date getDataTerminePre() {
		return dataTerminePre;
	}

	public void setDataTerminePre(Date dataTerminePre) {
		this.dataTerminePre = dataTerminePre;
	}

	public Date getDataDecorrenzaPost() {
		return dataDecorrenzaPost;
	}

	public void setDataDecorrenzaPost(Date dataDecorrenzaPost) {
		this.dataDecorrenzaPost = dataDecorrenzaPost;
	}

	public Date getDataTerminePost() {
		return dataTerminePost;
	}

	public void setDataTerminePost(Date dataTerminePost) {
		this.dataTerminePost = dataTerminePost;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
