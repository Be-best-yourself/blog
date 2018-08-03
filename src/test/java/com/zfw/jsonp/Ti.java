package com.zfw.jsonp;

import java.io.Serializable;

public class Ti implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String tiType;
	private String tiTiMu;
	private String tiXuanxiang;
	private Double tiLevel;
	private String tiBanben;
	private String tiXueKe;
	private String tiBanji;
	private String tiBanjiCemu;
	private String tiZhangjie;
	public String getTiZhangjie() {
		return tiZhangjie;
	}
	public void setTiZhangjie(String tiZhangjie) {
		this.tiZhangjie = tiZhangjie;
	}
	private String tiFrom;
	private Integer tiFromId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTiType() {
		return tiType;
	}
	public void setTiType(String tiType) {
		this.tiType = tiType;
	}
	public String getTiTiMu() {
		return tiTiMu;
	}
	public void setTiTiMu(String tiTiMu) {
		this.tiTiMu = tiTiMu;
	}
	public String getTiXuanxiang() {
		return tiXuanxiang;
	}
	public void setTiXuanxiang(String tiXuanxiang) {
		this.tiXuanxiang = tiXuanxiang;
	}
	public Double getTiLevel() {
		return tiLevel;
	}
	public void setTiLevel(Double tiLevel) {
		this.tiLevel = tiLevel;
	}
	public String getTiBanben() {
		return tiBanben;
	}
	public void setTiBanben(String tiBanben) {
		this.tiBanben = tiBanben;
	}
	public String getTiXueKe() {
		return tiXueKe;
	}
	public void setTiXueKe(String tiXueKe) {
		this.tiXueKe = tiXueKe;
	}
	public String getTiBanji() {
		return tiBanji;
	}
	public void setTiBanji(String tiBanji) {
		this.tiBanji = tiBanji;
	}
	public String getTiBanjiCemu() {
		return tiBanjiCemu;
	}
	public void setTiBanjiCemu(String tiBanjiCemu) {
		this.tiBanjiCemu = tiBanjiCemu;
	}
	public String getTiFrom() {
		return tiFrom;
	}
	public void setTiFrom(String tiFrom) {
		this.tiFrom = tiFrom;
	}
	public Integer getTiFromId() {
		return tiFromId;
	}
	public void setTiFromId(Integer tiFromId) {
		this.tiFromId = tiFromId;
	}
	
	
}
