package org.han.cpca;

/**
 * 地址解析结果类
 * 存储解析后的省、市、区信息和详细地址
 */
public class CpcaSeg {
	private String provinceName; // 省份名称
	private String provinceCode; // 省份编码
	private String cityName; // 城市名称
	private String cityCode; // 城市编码
	private String areaName; // 区县名称
	private String cpcaCode; // 6位行政区划编码
	private String address; // 不带省、市、县的详细地址
	private CpcaIndex provinceNameIndex; // 省份在原文本中的索引
	private CpcaIndex cityNameIndex; // 城市在原文本中的索引
	private CpcaIndex areaNameIndex; // 区县在原文本中的索引
	
	/**
	 * 获取省份名称
	 * @return 省份名称
	 */
	public String getProvinceName() {
		return provinceName;
	}
	
	/**
	 * 设置省份名称
	 * @param provinceName 省份名称
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	/**
	 * 获取省份编码
	 * @return 省份编码
	 */
	public String getProvinceCode() {
		return provinceCode;
	}
	
	/**
	 * 设置省份编码
	 * @param provinceCode 省份编码
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	/**
	 * 获取城市名称
	 * @return 城市名称
	 */
	public String getCityName() {
		return cityName;
	}
	
	/**
	 * 设置城市名称
	 * @param cityName 城市名称
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	/**
	 * 获取城市编码
	 * @return 城市编码
	 */
	public String getCityCode() {
		return cityCode;
	}
	
	/**
	 * 设置城市编码
	 * @param cityCode 城市编码
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	/**
	 * 获取区县名称
	 * @return 区县名称
	 */
	public String getAreaName() {
		return areaName;
	}
	
	/**
	 * 设置区县名称
	 * @param areaName 区县名称
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	/**
	 * 获取行政区划编码
	 * @return 6位行政区划编码
	 */
	public String getCpcaCode() {
		return cpcaCode;
	}
	
	/**
	 * 设置行政区划编码
	 * @param cpcaCode 6位行政区划编码
	 */
	public void setCpcaCode(String cpcaCode) {
		this.cpcaCode = cpcaCode;
	}
	
	/**
	 * 获取省份在原文本中的索引
	 * @return 省份索引对象
	 */
	public CpcaIndex getProvinceNameIndex() {
		return provinceNameIndex;
	}
	
	/**
	 * 设置省份在原文本中的索引
	 * @param provinceNameIndex 省份索引对象
	 */
	public void setProvinceNameIndex(CpcaIndex provinceNameIndex) {
		this.provinceNameIndex = provinceNameIndex;
	}
	
	/**
	 * 获取城市在原文本中的索引
	 * @return 城市索引对象
	 */
	public CpcaIndex getCityNameIndex() {
		return cityNameIndex;
	}
	
	/**
	 * 设置城市在原文本中的索引
	 * @param cityNameIndex 城市索引对象
	 */
	public void setCityNameIndex(CpcaIndex cityNameIndex) {
		this.cityNameIndex = cityNameIndex;
	}
	
	/**
	 * 获取区县在原文本中的索引
	 * @return 区县索引对象
	 */
	public CpcaIndex getAreaNameIndex() {
		return areaNameIndex;
	}
	
	/**
	 * 设置区县在原文本中的索引
	 * @param areaNameIndex 区县索引对象
	 */
	public void setAreaNameIndex(CpcaIndex areaNameIndex) {
		this.areaNameIndex = areaNameIndex;
	}
	
	/**
	 * 获取详细地址
	 * @return 不带省、市、县的详细地址
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * 设置详细地址
	 * @param address 不带省、市、县的详细地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 地址是否包含省、市、区至少一处信息
	 * @return 是否包含省市区信息
	 */
	public boolean hasPca() {
		return this.provinceName != null || this.cityName != null || this.areaName != null;
	}
	
	/**
	 * 地址无省、市、区信息
	 * @return 是否无省市区信息
	 */
	public boolean noPca() {
		return !hasPca();
	}
	
	/**
	 * 地址是否包含完整的省、市、区信息
	 * @return 是否包含完整的省市区信息
	 */
	public boolean fullPca() {
		return this.provinceName != null && this.cityName != null && this.areaName != null; 
	}
	
	/**
	 * 是否包含省份信息
	 * @return 是否包含省份信息
	 */
	public boolean hasProvince() {
		return this.provinceNameIndex != null; 
	}
	
	/**
	 * 是否包含城市信息
	 * @return 是否包含城市信息
	 */
	public boolean hasCity() {
		return this.cityNameIndex != null; 
	}
	
	/**
	 * 是否包含区县信息
	 * @return 是否包含区县信息
	 */
	public boolean hasArea() {
		return this.areaNameIndex != null; 
	}
	
	/**
	 * 创建一个空的地址解析结果对象
	 * @return 空的地址解析结果对象
	 */
	public static CpcaSeg none() {
		return new CpcaSeg();
	}
	
	/**
	 * 是否包含详细地址
	 * @return 是否包含详细地址
	 */
	public boolean hasAddress() {
		return this.address != null && !this.address.equals("");
	}
	
	/**
	 * 重置所有字段为null
	 */
	public void reset() {
		setProvinceName(null);
		setProvinceCode(null);
		setCityName(null);
		setCityCode(null);
		setAreaName(null);
		setAddress(null);
		setCpcaCode(null);
		setProvinceNameIndex(null);
		setCityNameIndex(null);
		setAreaNameIndex(null);
	}
}

/**
 * 地址索引类
 * 存储地址在原文本中的起始和结束位置
 */
class CpcaIndex {
	private Integer beginIndex; // 起始位置
	private Integer endIndex; // 结束位置
	
	/**
	 * 默认构造函数
	 */
	public CpcaIndex() {
	}
	
	/**
	 * 构造函数
	 * @param beginIndex 起始位置
	 * @param endIndex 结束位置
	 */
	public CpcaIndex(Integer beginIndex, Integer endIndex) {
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
	}
	
	/**
	 * 获取起始位置
	 * @return 起始位置
	 */
	public Integer getBeginIndex() {
		return beginIndex;
	}
	
	/**
	 * 设置起始位置
	 * @param beginIndex 起始位置
	 */
	public void setBeginIndex(Integer beginIndex) {
		this.beginIndex = beginIndex;
	}
	
	/**
	 * 获取结束位置
	 * @return 结束位置
	 */
	public Integer getEndIndex() {
		return endIndex;
	}
	
	/**
	 * 设置结束位置
	 * @param endIndex 结束位置
	 */
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
}
