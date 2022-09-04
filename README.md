---


title: Prometheus v1.0.0


---

<a name="Prometheus"></a>
# Prometheus

> 接口版本：v1.0.0
> prometheus版本：2.38

<a name="5dc66741"></a>
## GET 查询series

GET /api/v1/series

<a name="527466ff"></a>
### 请求参数
| 名称 | 位置 | 类型 | 必选 | 说明 |
| --- | --- | --- | --- | --- |
| match[] | query | array | 是 | selector选择器 |


> 返回示例


> 成功


```json
{
  "status": "success",
  "data": [
    {
      "__name__": "up",
      "instance": "192.168.2.130:8081",
      "job": "spring-boot"
    },
    {
      "__name__": "up",
      "instance": "http://192.168.2.131:9090",
      "job": "http_probe_status"
    },
    {
      "__name__": "up",
      "instance": "http://192.168.2.131:9090",
      "job": "http_probe_status",
      "project": "运维工作台",
      "service": "普罗米修斯服务"
    }
  ]
}
```

<a name="75ef6f10"></a>
### 返回结果
| 状态码 | 状态码含义 | 说明 | 数据模型 |
| --- | --- | --- | --- |
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |


<a name="d13701ef"></a>
### 返回数据结构

<a name="391c2812"></a>
## GET 范围查询

GET /api/v1/query_range

<a name="527466ff-1"></a>
### 请求参数
| 名称 | 位置 | 类型 | 必选 | 说明 |
| --- | --- | --- | --- | --- |
| query | query | string | 是 | promSql |
| start | query | string | 是 | 开始时间（毫秒值） |
| end | query | string | 是 | 结束时间 |
| step | query | string | 是 | 步长 |


> 返回示例


> 成功


```json
{
  "status": "success",
  "data": {
    "resultType": "matrix",
    "result": [
      {
        "metric": {
          "__name__": "up",
          "instance": "192.168.2.130:8081",
          "job": "spring-boot"
        },
        "values": [
          [
            1662177210,
            "0"
          ],
          [
            1662177270,
            "0"
          ],
          [
            1662177330,
            "0"
          ]
        ]
      },
      {
        "metric": {
          "__name__": "up",
          "instance": "192.168.2.131:18104",
          "job": "docker"
        },
        "values": [
          [
            1662177210,
            "0"
          ],
          [
            1662177270,
            "0"
          ],
          [
            1662177330,
            "0"
          ]
      {
        "metric": {
          "__name__": "up",
          "instance": "http://192.168.2.131:9090",
          "job": "http_probe_status",
          "project": "运维工作台",
          "service": "普罗米修斯服务"
        },
        "values": [
          [
            1662177210,
            "1"
          ],
          [
            1662177270,
            "1"
          ],
          [
            1662177330,
            "1"
          ]
        ]
      }
    ]
  }
}
```


<a name="75ef6f10-1"></a>
### 返回结果
| 状态码 | 状态码含义 | 说明 | 数据模型 |
| --- | --- | --- | --- |
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |


<a name="d13701ef-1"></a>
### 返回数据结构

<a name="714758ab"></a>
## GET 查询告警

GET /api/v1/alertmanagers

> 返回示例


> 成功


```json
{
  "status": "success",
  "data": {
    "activeAlertmanagers": [],
    "droppedAlertmanagers": []
  }
}
```

<a name="75ef6f10-2"></a>
### 返回结果
| 状态码 | 状态码含义 | 说明 | 数据模型 |
| --- | --- | --- | --- |
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |


<a name="d13701ef-2"></a>
### 返回数据结构

<a name="16fe10cf"></a>
## GET 查询状态配置

GET /api/v1/status/config

> 返回示例


> 成功


```json
{
  "status": "success",
  "data": {
    "yaml": "global:\n  scrape_interval: 15s\n  scrape_timeout: 10s\n  evaluation_interval: 15s\nalerting:\n  alertmanagers:\n  - follow_redirects: true\n    enable_http2: true\n    scheme: http\n    timeout: 10s\n    api_version: v2\n    static_configs:\n    - targets: []\nscrape_configs:\n- job_name: prometheus\n  honor_timestamps: true\n  scrape_interval: 15s\n  scrape_timeout: 10s\n  metrics_path: /metrics\n  scheme: http\n  follow_redirects: true\n  enable_http2: true\n  static_configs:\n  - targets:\n    - 192.168.2.131:9090\n- job_name: node\n  honor_timestamps: true\n  scrape_interval: 15s\n  scrape_timeout: 10s\n  metrics_path: /metrics\n  scheme: http\n  follow_redirects: true\n  enable_http2: true\n  static_configs:\n  - targets:\n    - 192.168.2.131:9100\n- job_name: docker\n  honor_timestamps: true\n  scrape_interval: 15s\n  scrape_timeout: 10s\n  metrics_path: /metrics\n  scheme: http\n  follow_redirects: true\n  enable_http2: true\n  static_configs:\n  - targets:\n    - 192.168.2.131:18104\n- job_name: spring-boot\n  honor_timestamps: true\n  scrape_interval: 5s\n  scrape_timeout: 5s\n  metrics_path: /actuator/prometheus\n  scheme: http\n  follow_redirects: true\n  enable_http2: true\n  static_configs:\n  - targets:\n    - 192.168.2.130:8081\n- job_name: http_probe_status\n  honor_timestamps: true\n  params:\n    module:\n    - http_2xx\n  scrape_interval: 10s\n  scrape_timeout: 10s\n  metrics_path: /probe\n  scheme: http\n  follow_redirects: true\n  enable_http2: true\n  relabel_configs:\n  - source_labels: [__address__]\n    separator: ;\n    regex: (.*)\n    target_label: __param_target\n    replacement: $1\n    action: replace\n  - source_labels: [__param_target]\n    separator: ;\n    regex: (.*)\n    target_label: instance\n    replacement: $1\n    action: replace\n  - separator: ;\n    regex: (.*)\n    target_label: __address__\n    replacement: 192.168.2.131:9115\n    action: replace\n  static_configs:\n  - targets:\n    - http://192.168.2.131:9090\n    labels:\n      project: 运维工作台\n      service: 普罗米修斯服务\n  - targets:\n    - http://192.168.2.131:3000\n    labels:\n      project: 运维工作台\n      service: 监控服务\n  - targets:\n    - http://192.168.2.130:8081/actuator\n    labels:\n      project: 数据采集系统\n      service: 苏林AI\n"
  }
}
```

<a name="75ef6f10-3"></a>
### 返回结果
| 状态码 | 状态码含义 | 说明 | 数据模型 |
| --- | --- | --- | --- |
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |


<a name="d13701ef-3"></a>
### 返回数据结构

<a name="1fc4434c"></a>
## GET 查询targets

GET /api/v1/targets

> 返回示例


> 成功


```json
{
  "status": "success",
  "data": {
    "activeTargets": [
      {
        "discoveredLabels": {
          "__address__": "192.168.2.131:18104",
          "__metrics_path__": "/metrics",
          "__scheme__": "http",
          "__scrape_interval__": "15s",
          "__scrape_timeout__": "10s",
          "job": "docker"
        },
        "labels": {
          "instance": "192.168.2.131:18104",
          "job": "docker"
        },
        "scrapePool": "docker",
        "scrapeUrl": "http://192.168.2.131:18104/metrics",
        "globalUrl": "http://192.168.2.131:18104/metrics",
        "lastError": "Get \"http://192.168.2.131:18104/metrics\": dial tcp 192.168.2.131:18104: connect: connection refused",
        "lastScrape": "2022-09-03T11:56:10.986677045+08:00",
        "lastScrapeDuration": 0.00034947,
        "health": "down",
        "scrapeInterval": "15s",
        "scrapeTimeout": "10s"
      },
      {
        "discoveredLabels": {
          "__address__": "http://192.168.2.130:8081/actuator",
          "__metrics_path__": "/probe",
          "__param_module": "http_2xx",
          "__scheme__": "http",
          "__scrape_interval__": "10s",
          "__scrape_timeout__": "10s",
          "job": "http_probe_status",
          "project": "数据采集系统",
          "service": "苏林AI"
        },
        "labels": {
          "instance": "http://192.168.2.130:8081/actuator",
          "job": "http_probe_status",
          "project": "数据采集系统",
          "service": "苏林AI"
        },
        "scrapePool": "http_probe_status",
        "scrapeUrl": "http://192.168.2.131:9115/probe?module=http_2xx&target=http%3A%2F%2F192.168.2.130%3A8081%2Factuator",
        "globalUrl": "http://192.168.2.131:9115/probe?module=http_2xx&target=http%3A%2F%2F192.168.2.130%3A8081%2Factuator",
        "lastError": "",
        "lastScrape": "2022-09-03T11:56:03.800209322+08:00",
        "lastScrapeDuration": 9.503235968,
        "health": "up",
        "scrapeInterval": "10s",
        "scrapeTimeout": "10s"
      },
      {
        "discoveredLabels": {
          "__address__": "192.168.2.130:8081",
          "__metrics_path__": "/actuator/prometheus",
          "__scheme__": "http",
          "__scrape_interval__": "5s",
          "__scrape_timeout__": "5s",
          "job": "spring-boot"
        },
        "labels": {
          "instance": "192.168.2.130:8081",
          "job": "spring-boot"
        },
        "scrapePool": "spring-boot",
        "scrapeUrl": "http://192.168.2.130:8081/actuator/prometheus",
        "globalUrl": "http://192.168.2.130:8081/actuator/prometheus",
        "lastError": "Get \"http://192.168.2.130:8081/actuator/prometheus\": context deadline exceeded",
        "lastScrape": "2022-09-03T11:56:05.086009549+08:00",
        "lastScrapeDuration": 5.001295654,
        "health": "down",
        "scrapeInterval": "5s",
        "scrapeTimeout": "5s"
      }
    ],
    "droppedTargets": []
  }
}
```

<a name="75ef6f10-4"></a>
### 返回结果
| 状态码 | 状态码含义 | 说明 | 数据模型 |
| --- | --- | --- | --- |
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |


<a name="d13701ef-4"></a>
### 返回数据结构

<a name="1c542b03"></a>
## GET 查询label

GET /api/v1/label/{label}/values

<a name="527466ff-2"></a>
### 请求参数
| 名称 | 位置 | 类型 | 必选 | 说明 |
| --- | --- | --- | --- | --- |
| label | path | string | 是 | 标签 |


> 返回示例


> 成功


```json
{
  "status": "success",
  "data": [
    "数据采集系统",
    "运维工作台"
  ]
}
```

<a name="75ef6f10-5"></a>
### 返回结果
| 状态码 | 状态码含义 | 说明 | 数据模型 |
| --- | --- | --- | --- |
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |


<a name="d13701ef-5"></a>
### 返回数据结构

<a name="c669548b"></a>
## GET 查询cpu使用率

GET /api/v1/query

<a name="527466ff-3"></a>
### 请求参数
| 名称 | 位置 | 类型 | 必选 | 说明 |
| --- | --- | --- | --- | --- |
| query | query | string | 是 | promSql |


> 返回示例


> 成功


```json
{
  "status": "success",
  "data": {
    "resultType": "vector",
    "result": [
      {
        "metric": {},
        "value": [
          1662178168.691,
          "0.15665622291844983"
        ]
      }
    ]
  }
}
```

<a name="75ef6f10-6"></a>
### 返回结果
| 状态码 | 状态码含义 | 说明 | 数据模型 |
| --- | --- | --- | --- |
| 200 | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功 | Inline |


<a name="d13701ef-6"></a>
### 返回数据结构

<a name="cb17b4e2"></a>
# 数据模型
