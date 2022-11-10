package datatypesWS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.util.Set;

import datatypes.DtPremio;
import datatypes.DtSocioExtra;


@XmlAccessorType(XmlAccessType.FIELD)
public class DtSocioWS extends DtUsuarioWS{
	private String[] clasesDeActividadesHead;
	private String[][] clasesDeActividadesData;
	private String[] clasesDeActividadesFinalizadasHead;
	private String[][] clasesDeActividadesFinalizadasData;
	private String[] cuponerasCompradas;
	private String[] actividadesFavoritas;
	private String[] premiosHead;
	private DtPremioWS[] premiosData;
	
	
	public DtSocioWS() {
		super();
	}
	public DtSocioWS(DtSocioExtra d) {
		super(d);
		clasesDeActividadesHead = new String[d.getAguadeUwu().size()];
		clasesDeActividadesData = new String[d.getAguadeUwu().size()][];
		int i=0;
		for(Entry<String, Set<String>> x: d.getAguadeUwu().entrySet()) {
			clasesDeActividadesHead[i] = x.getKey();
			clasesDeActividadesData[i++] = x.getValue().toArray(new String[0]);
		}
		clasesDeActividadesFinalizadasHead = new String[d.getClasesDeActividadesFinalizadas().size()];
		clasesDeActividadesFinalizadasData = new String[d.getClasesDeActividadesFinalizadas().size()][];
		i=0;
		for(Entry<String, Set<String>> x: d.getClasesDeActividadesFinalizadas().entrySet()) {
			clasesDeActividadesFinalizadasHead[i] = x.getKey();
			clasesDeActividadesFinalizadasData[i++] = x.getValue().toArray(new String[0]);
		}
		i=0;
		premiosHead = new String[d.getPremios().size()];
		premiosData = new DtPremioWS[d.getPremios().size()];
		for(Entry<String, DtPremio> x: d.getPremios().entrySet()) {
			premiosHead[i] = x.getKey();
			premiosData[i++] = new DtPremioWS(x.getValue());
		}
		cuponerasCompradas = d.getCuponerasCompradas().toArray(new String[0]);
		actividadesFavoritas = d.getActividadesFavoritas().toArray(new String[0]);
	}
	
	@Override
	public DtSocioExtra adapt() {
		Map<String,  Set<String>> clasesDeActividades = new HashMap<>();
		for(int i=0; clasesDeActividadesHead!=null && i<clasesDeActividadesHead.length; i++) {
			clasesDeActividades.put(clasesDeActividadesHead[i], new HashSet<>(Arrays.asList(clasesDeActividadesData[i])));
		}
		Map<String,  Set<String>> clasesDeActividadesFinalizadas = new HashMap<>();
		for(int i=0; clasesDeActividadesFinalizadasHead!=null && i<clasesDeActividadesFinalizadasHead.length; i++) {
			clasesDeActividadesFinalizadas.put(clasesDeActividadesFinalizadasHead[i], new HashSet<>(Arrays.asList(clasesDeActividadesFinalizadasData[i])));
		}
		Map<String,  DtPremio> premios = new HashMap<>();
		for(int i=0; premiosHead!=null && i<premiosHead.length; i++) {
			premios.put(premiosHead[i], premiosData[i].adapt());
		}
		if(this.getSeguidosNickname()==null)
			this.setSeguidosNickname(new String[0]);
		if(this.getSeguidoresNickname()==null)
			this.setSeguidoresNickname(new String[0]);
		if(this.getCuponerasCompradas()==null)
			this.setCuponerasCompradas(new String[0]);
		if(this.getActividadesFavoritas()==null)
			this.setActividadesFavoritas(new String[0]);
		
		return new DtSocioExtra(this.getNickname(),this.getNombre(),this.getApellido(),this.getEmail(),
				this.getContrasenia(), this.getFechaNacimiento().adapt(), clasesDeActividades, this.getImagen(),
				new HashSet<String>(Arrays.asList(this.getSeguidosNickname())), new HashSet<String>(Arrays.asList(this.getSeguidoresNickname())),
				new HashSet<String>(Arrays.asList(cuponerasCompradas)), new HashSet<String>(Arrays.asList(actividadesFavoritas)), premios, clasesDeActividadesFinalizadas);
	}

	public String[] getClasesDeActividadesHead() {
		return clasesDeActividadesHead;
	}


	public void setClasesDeActividadesHead(String[] clasesDeActividadesHead) {
		this.clasesDeActividadesHead = clasesDeActividadesHead;
	}


	public String[][] getClasesDeActividadesData() {
		return clasesDeActividadesData;
	}


	public void setClasesDeActividadesData(String[][] clasesDeActividadesData) {
		this.clasesDeActividadesData = clasesDeActividadesData;
	}


	public String[] getClasesDeActividadesFinalizadasHead() {
		return clasesDeActividadesFinalizadasHead;
	}


	public void setClasesDeActividadesFinalizadasHead(String[] clasesDeActividadesFinalizadasHead) {
		this.clasesDeActividadesFinalizadasHead = clasesDeActividadesFinalizadasHead;
	}


	public String[][] getClasesDeActividadesFinalizadasData() {
		return clasesDeActividadesFinalizadasData;
	}


	public void setClasesDeActividadesFinalizadasData(String[][] clasesDeActividadesFinalizadasData) {
		this.clasesDeActividadesFinalizadasData = clasesDeActividadesFinalizadasData;
	}


	public String[] getCuponerasCompradas() {
		return cuponerasCompradas;
	}


	public void setCuponerasCompradas(String[] cuponerasCompradas) {
		this.cuponerasCompradas = cuponerasCompradas;
	}


	public String[] getActividadesFavoritas() {
		return actividadesFavoritas;
	}


	public void setActividadesFavoritas(String[] actividadesFavoritas) {
		this.actividadesFavoritas = actividadesFavoritas;
	}

	public String[] getPremiosHead() {
		return premiosHead;
	}

	public void setPremiosHead(String[] premiosHead) {
		this.premiosHead = premiosHead;
	}

	public DtPremioWS[] getPremiosData() {
		return premiosData;
	}

	public void setPremiosData(DtPremioWS[] premiosData) {
		this.premiosData = premiosData;
	}

}
