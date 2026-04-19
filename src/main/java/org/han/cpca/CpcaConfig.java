package org.han.cpca;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 地址解析配置类
 * 负责创建和配置地址解析器实例
 */
@Configuration
public class CpcaConfig {
    
    @Value("${cpca.csv.file}")
    private String cpcaCvsFile; // 行政区划代码CSV文件路径
    
    /**
     * 创建地址解析器实例
     * @return 地址解析器实例
     */
    @Bean
    public CpcaExtractor cpcaExtractor() {
        return CpcaExtractors.builder()
                .withCpcaCvsFile(cpcaCvsFile)
                .build();
    }
}