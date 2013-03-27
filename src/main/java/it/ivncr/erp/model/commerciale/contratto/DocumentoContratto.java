package it.ivncr.erp.model.commerciale.contratto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "con_documento_contratto")
public class DocumentoContratto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "con_documento_contratto_id_seq")
	@SequenceGenerator(name = "con_documento_contratto_id_seq", sequenceName = "con_documento_contratto_id_seq")
	@Column(name="id")
	private Integer id;

    @ManyToOne
    @JoinColumn(name="contratto_id")
    private Contratto contratto;

	@Column(name="descrizione")
	private String descrizione;

	@Column(name="filename")
	private String filename;

	@Column(name="mime_type")
	private String mimeType;

	@Column(name="caricamento_ts")
	private Date caricamento;

	@Lob
	@Column(name="documento")
	private byte[] documento;

	@Column(name="note")
	private String note;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("contratto", contratto, false)
			.append("descrizione", descrizione)
			.append("filename", filename)
			.append("mimeType", mimeType)
			.append("caricamento", caricamento)
			.append("documento", documento, false)
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Date getCaricamento() {
		return caricamento;
	}

	public void setCaricamento(Date caricamento) {
		this.caricamento = caricamento;
	}

	public byte[] getDocumento() {
		return documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
