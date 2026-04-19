package org.han.cpca;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 地址解析控制器
 * 提供HTTP接口用于地址信息的提取和数据重新加载
 */
@RestController
@RequestMapping("/api/cpca")
public class CpcaController {
    
    private final CpcaExtractor cpcaExtractor;
    
    /**
     * 构造函数
     * @param cpcaExtractor 地址解析器实例
     */
    @Autowired
    public CpcaController(CpcaExtractor cpcaExtractor) {
        this.cpcaExtractor = cpcaExtractor;
    }
    
    /**
     * 提取地址信息
     * @param request 包含待解析地址和配置参数的请求对象
     * @return 解析结果，包含省、市、区信息和详细地址
     */
    @PostMapping("/extract")
    public Map<String, Object> extract(@RequestBody ExtractRequest request) {
        Map<String, String> umap = request.getUmap();
        boolean strictlyMatch = request.isStrictlyMatch();
        
        // 调用解析器进行地址解析
        CpcaSeg cpcaSeg = cpcaExtractor.transform(request.getLocation(), umap, strictlyMatch);
        
        // 构建返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("provinceName", cpcaSeg.getProvinceName());
        result.put("provinceCode", cpcaSeg.getProvinceCode());
        result.put("cityName", cpcaSeg.getCityName());
        result.put("cityCode", cpcaSeg.getCityCode());
        result.put("areaName", cpcaSeg.getAreaName());
        result.put("cpcaCode", cpcaSeg.getCpcaCode());
        result.put("address", cpcaSeg.getAddress());
        result.put("fullPca", cpcaSeg.fullPca());
        result.put("noPca", cpcaSeg.noPca());
        
        // 如果需要包含索引信息
        if (request.isIncludeIndex()) {
            Map<String, Object> indexMap = new HashMap<>();
            if (cpcaSeg.getProvinceNameIndex() != null) {
                indexMap.put("province", cpcaSeg.getProvinceNameIndex());
            }
            if (cpcaSeg.getCityNameIndex() != null) {
                indexMap.put("city", cpcaSeg.getCityNameIndex());
            }
            if (cpcaSeg.getAreaNameIndex() != null) {
                indexMap.put("area", cpcaSeg.getAreaNameIndex());
            }
            result.put("index", indexMap);
        }
        
        return result;
    }
    
    /**
     * 重新加载行政区划数据
     * @return 操作结果
     */
    @PostMapping("/reload")
    public Map<String, Object> reload() {
        Map<String, Object> result = new HashMap<>();
        try {
            if (cpcaExtractor instanceof CpcaExtractorImpl) {
                ((CpcaExtractorImpl) cpcaExtractor).reloadCpca();
                result.put("success", true);
                result.put("message", "数据重新加载成功");
            } else {
                result.put("success", false);
                result.put("message", "当前实例不支持重新加载");
            }
        } catch (IOException e) {
            result.put("success", false);
            result.put("message", "数据重新加载失败：" + e.getMessage());
        }
        return result;
    }
    
    /**
     * 地址提取请求对象
     */
    public static class ExtractRequest {
        private String location; // 待解析的地址文本
        private Map<String, String> umap; // 自定义映射表，用于处理同名地区
        private boolean strictlyMatch; // 是否严格匹配
        private boolean includeIndex; // 是否包含索引信息
        
        public String getLocation() {
            return location;
        }
        
        public void setLocation(String location) {
            this.location = location;
        }
        
        public Map<String, String> getUmap() {
            return umap;
        }
        
        public void setUmap(Map<String, String> umap) {
            this.umap = umap;
        }
        
        public boolean isStrictlyMatch() {
            return strictlyMatch;
        }
        
        public void setStrictlyMatch(boolean strictlyMatch) {
            this.strictlyMatch = strictlyMatch;
        }
        
        public boolean isIncludeIndex() {
            return includeIndex;
        }
        
        public void setIncludeIndex(boolean includeIndex) {
            this.includeIndex = includeIndex;
        }
    }
}