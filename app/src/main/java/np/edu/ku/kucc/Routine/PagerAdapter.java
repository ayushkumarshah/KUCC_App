package np.edu.ku.kucc.Routine;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm, int NoOfTabs) {
        super(fm);
        this.mNoOfTabs=NoOfTabs;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Sunday sunday = new Sunday();
                return sunday;
            case 1:
                Monday monday = new Monday();
                return monday;
            case 2:
                Tuesday tuesday = new Tuesday();
                return tuesday;
            case 3:
                Wednesday wednesday=new Wednesday();
                return wednesday;
            case 4:
                Thursday thursday=new Thursday();
                return thursday;
            case 5:
                Friday friday=new Friday();
                return friday;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
