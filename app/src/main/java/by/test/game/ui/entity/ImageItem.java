package by.test.game.ui.entity;

/**
 * Created by kirila on 27.2.17.
 */
public class ImageItem {

    private String mLabel;
    private String mImagePath;
    private boolean mExcess;

    public ImageItem() {

    }

    public ImageItem(String label, String imagePath, boolean excess) {
        mLabel = label;
        mImagePath = imagePath;
        mExcess = excess;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public void setImagePath(String imagePath) {
        mImagePath = imagePath;
    }

    public boolean isExcess() {
        return mExcess;
    }

    public void setExcess(boolean excess) {
        mExcess = excess;
    }
}
