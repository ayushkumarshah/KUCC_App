package np.edu.ku.kucc.Account;

public class EventModel {
    private String mTitle, mVenue, mRoomNo, mInformation, mDate, mTime;
    private int mEventID, mContact;

    public EventModel() {
    }

    public EventModel(int mEventID, String mTitle, String mVenue, String mRoomNo, String mInformation, int mContact, String mDate, String mTime) {
        this.mEventID = mEventID;
        this.mTitle = mTitle;
        this.mVenue = mVenue;
        this.mRoomNo = mRoomNo;
        this.mInformation = mInformation;
        this.mContact = mContact;
        this.mDate = mDate;
        this.mTime = mTime;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmVenue() {
        return mVenue;
    }

    public void setmVenue(String mVenue) {
        this.mVenue = mVenue;
    }

    public String getmRoomNo() {
        return mRoomNo;
    }

    public void setmRoomNo(String mRoomNo) {
        this.mRoomNo = mRoomNo;
    }

    public String getmInformation() {
        return mInformation;
    }

    public void setmInformation(String mInformation) {
        this.mInformation = mInformation;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public int getmEventID() {
        return mEventID;
    }

    public void setmEventID(int mEventID) {
        this.mEventID = mEventID;
    }

    public int getmContact() {
        return mContact;
    }

    public void setmContact(int mContact) {
        this.mContact = mContact;
    }
}