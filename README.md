# jcpca

## 项目概述

jcpca 是一个 Java 版本的中国地址解析工具，用于从地址文本中提取省、市、区（县）信息。该工具使用 Aho-Corasick 算法实现高效的地址匹配，支持处理各种复杂的地址格式。

## 功能特性

- 从地址文本中提取省、市、区（县）信息
- 支持处理带省、市、区（县）全称和简称的地址
- 支持自定义映射表，处理同名地区的情况
- 提供 HTTP 接口，方便集成到 Web 应用中
- 支持重新加载行政区划数据，无需重启应用
- 高性能设计，适合处理大量地址数据

## 技术栈

- Java 8+
- Spring Boot
- Aho-Corasick Double Array Trie（分词器工具）
- Jackson（JSON 序列化）

## 数据来源

省市区数据来自国家统计局网站公开数据，参考以下来源：
- https://github.com/DQinYuan/chinese_province_city_area_mapper
- https://github.com/Vonng/adcode
- http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/

## 安装和依赖

### Maven 依赖

```xml
<dependency>
    <groupId>org.han</groupId>
    <artifactId>jcpca</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 配置文件

在 `application.properties` 文件中配置行政区划代码 CSV 文件路径：

```properties
cpca.csv.file=adcodes.csv
```

## 使用示例

### 1. Java 代码使用

```java
// 创建地址解析器
CpcaExtractor cpcaExtractor = CpcaExtractors.builder()
        .withCpcaCvsFile("adcodes.csv")
        .build();

// 解析地址
CpcaSeg cpcaSeg = cpcaExtractor.transform("浙江省杭州市拱墅区祥园路300号");
System.out.println(cpcaExtractor.encodeJson(cpcaSeg));

// 处理同名地区
Map<String, String> umap = new HashMap<>();
umap.put("朝阳区", "110105"); // 指定使用北京市朝阳区
CpcaSeg cpcaSeg2 = cpcaExtractor.transform("朝阳区汉庭酒店大山子店", umap);
System.out.println(cpcaExtractor.encodeJson(cpcaSeg2));
```

### 2. HTTP 接口使用

#### 提取地址信息

**请求**：
```http
POST /api/cpca/extract
Content-Type: application/json

{
  "location": "浙江省杭州市拱墅区祥园路300号",
  "umap": {},
  "strictlyMatch": false,
  "includeIndex": true
}
```

**响应**：
```json
{
  "provinceName": "浙江省",
  "provinceCode": "330000",
  "cityName": "杭州市",
  "cityCode": "330100",
  "areaName": "拱墅区",
  "cpcaCode": "330105",
  "address": "祥园路300号",
  "fullPca": true,
  "noPca": false,
  "index": {
    "province": {"beginIndex": 0, "endIndex": 3},
    "city": {"beginIndex": 3, "endIndex": 6},
    "area": {"beginIndex": 6, "endIndex": 9}
  }
}
```

#### 重新加载数据

**请求**：
```http
POST /api/cpca/reload
```

**响应**：
```json
{
  "success": true,
  "message": "数据重新加载成功"
}
```

## API 文档

### 核心类

#### CpcaExtractor

地址解析器接口，定义了地址解析的核心方法。

- `CpcaSeg transform(String location, Map<String, String> umap, boolean strictlyMatch)`：解析地址文本
  - `location`：待解析的地址文本
  - `umap`：自定义映射表，用于处理同名地区
  - `strictlyMatch`：是否严格匹配
  - 返回：解析后的地址信息对象

#### CpcaSeg

地址解析结果类，存储解析后的省、市、区信息和详细地址。

- `String getProvinceName()`：获取省份名称
- `String getProvinceCode()`：获取省份编码
- `String getCityName()`：获取城市名称
- `String getCityCode()`：获取城市编码
- `String getAreaName()`：获取区县名称
- `String getCpcaCode()`：获取行政区划编码
- `String getAddress()`：获取详细地址
- `boolean fullPca()`：是否包含完整的省、市、区信息
- `boolean noPca()`：是否无省、市、区信息

#### CpcaExtractors

地址解析器工厂类，用于创建地址解析器实例。

- `static Builder builder()`：创建构建器
- `Builder withCpcaCvsFile(String file)`：设置行政区划代码 CSV 文件路径
- `CpcaExtractor build()`：构建地址解析器实例

## 性能说明

- 使用 Aho-Corasick 算法实现高效的地址匹配
- 支持处理大量地址数据，适合在生产环境中使用
- 重新加载数据时使用同步机制，确保数据一致性

## 贡献指南

欢迎提交 Issue 和 Pull Request 来改进这个项目。

## 许可证

MIT License

## 联系方式

如有问题或建议，请通过 GitHub Issues 与我们联系。

