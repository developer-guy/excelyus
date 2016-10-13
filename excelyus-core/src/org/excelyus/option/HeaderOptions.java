package org.excelyus.option;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Batuhan Apaydin<br/>
 * Date: 13.10.2016<br/>
 * Time: 15:14<br/>
 * To change this template use File | Settings | File Templates.
 */
public class HeaderOptions {
    private short fontHeightInPoints;
    private short colorIndex;
    private boolean isBold;

    public HeaderOptions() {
    }

    public HeaderOptions(short fontHeightInPoints, short colorIndex, boolean isBold) {
        this.fontHeightInPoints = fontHeightInPoints;
        this.colorIndex = colorIndex;
        this.isBold = isBold;
    }

    public short getFontHeightInPoints() {
        return fontHeightInPoints;
    }

    public void setFontHeightInPoints(short fontHeightInPoints) {
        this.fontHeightInPoints = fontHeightInPoints;
    }

    public short getColorIndex() {
        return colorIndex;
    }

    public void setColorIndex(short colorIndex) {
        this.colorIndex = colorIndex;
    }

    public boolean isBold() {
        return isBold;
    }

    public void setBold(boolean bold) {
        isBold = bold;
    }
}
