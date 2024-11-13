package com.harper.interview.entity;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.interview.entity
 * @Author: liuhb_mios_ah
 * @CreateTime: 2024-05-21  17:53
 * @Description: TODO
 * @Version: 1.0
 */
public class MapResultEntity {
    /**
     * 返回结果状态值。
     * <p>
     * 0表示失败；
     * 1表示成功。
     */
    private String status;

    /**
     * 返回状态说明。
     * <p>
     * 成功时返回"OK"；
     * 失败时返回具体错误原因。
     */
    private String info;

    /**
     * 状态码。
     * <p>
     * 10000代表正确；
     * 其他值请参考info状态表。
     */
    private String infocode;

    /**
     * 省份或直辖市名称。
     * <p>
     * 若查询IP位于局域网网段内，则返回“局域网”；
     * 非法IP以及国外IP则返回空字符串。
     */
    private String province;

    /**
     * 城市名称。
     * <p>
     * 若为直辖市则与province相同；
     * 局域网IP、非法IP或国外IP查询时返回空字符串。
     */
    private String city;

    /**
     * 城市的行政区划代码。
     */
    private String adcode;

    /**
     * 所在城市矩形区域范围。
     * <p>
     * 格式为"左下经纬度,右上经纬度"，
     * 例如："116.0119343,39.66127144;116.7829835,40.2164962"。
     */
    private String rectangle;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getRectangle() {
        return rectangle;
    }

    public void setRectangle(String rectangle) {
        this.rectangle = rectangle;
    }
}
