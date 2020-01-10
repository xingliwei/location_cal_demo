package com.levyx.location.cal;

/**
 * Copyright (C), 2002-2020, 苏宁易购电子商务有限公司
 * FileName: LocationConvertProcess
 * Description:
 * Date: 2020/1/6 23:36
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名     修改时间        版本号       描述
 */
public interface LocationParseProcess {

    //初始化参数
    void initParam(double x0,double y0,
                   double x1,double y1,
                   double x2,double y2,
                   double x3,double y3,
                   double Sync_x,double Sync_y);

    //文件解析
    void parseData(String line);

    void readFile(String filePath);

}
