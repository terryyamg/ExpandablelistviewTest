package tw.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	int[] listIv; //圖示
	List<String> listDataHeader; //標題
	HashMap<String, List<String>> listDataChild; //內容

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 取得ExpandableListView
		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		// 列表資料
		prepareListData();

		/* listIv-圖示, listDataHeader-標題, listDataChild-內容*/
		listAdapter = new ExpandableListAdapter(this,listIv, listDataHeader,
				listDataChild);

		// 將列表資料加入至展開列表單
		expListView.setAdapter(listAdapter);

		// 點選標題 展開 監聽事件
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			public void onGroupExpand(int groupPosition) {
				Toast.makeText(getApplicationContext(),
						"您正在收看:" + listDataHeader.get(groupPosition),
						Toast.LENGTH_SHORT).show();
			}
		});

		// 點選標題監聽事件
		expListView.setOnChildClickListener(new OnChildClickListener() {

			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Toast.makeText(
						getApplicationContext(),
						"開始閱讀:"
								+ listDataHeader.get(groupPosition)
								+ " : "
								+ listDataChild.get(
										listDataHeader.get(groupPosition)).get(
										childPosition), Toast.LENGTH_SHORT)
						.show();
				return false;
			}
		});

		// 點選標題 收回 監聽事件
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			public void onGroupCollapse(int groupPosition) {
				Toast.makeText(getApplicationContext(),
						"放回書架:" + listDataHeader.get(groupPosition),
						Toast.LENGTH_SHORT).show();

			}
		});

	}

	/* 列表資料 */
	private void prepareListData() {
		listIv = new int[]{R.drawable.p1,R.drawable.p2,R.drawable.p3}; //圖示
		listDataHeader = new ArrayList<String>(); // 標題
		listDataChild = new HashMap<String, List<String>>(); // 內容

		listDataHeader.add("水滸傳");
		listDataHeader.add("紅樓夢");
		listDataHeader.add("三國演義");

		// Adding child data
		List<String> first = new ArrayList<String>();
		first.add("第一回　　張天師祈禳瘟疫　洪太尉誤走妖魔");
		first.add("第二回　　王教頭私走延安府　九紋龍大鬧史家村");
		first.add("第三回　　史大郎夜走華陰縣　魯提轄拳打鎮關西");
		first.add("第四回　　趙員外重修文殊院　魯智深大鬧五臺山");
		first.add("第五回　　小霸王醉入銷金帳　花和尚大鬧桃花村");

		List<String> second = new ArrayList<String>();
		second.add("第一回　　甄士隱夢幻識通靈　賈雨村風塵懷閨秀");
		second.add("第二回　　賈夫人仙逝揚州城　冷子興演說榮國府");
		second.add("第三回 　　託內兄如海薦西賓　接外孫賈母惜孤女");
		second.add("第四回　　薄命女偏逢薄命郎　葫蘆僧判斷葫蘆案");
		second.add("第五回　　賈寶玉神遊太虛境　警幻仙曲演紅樓夢");

		List<String> end = new ArrayList<String>();
		end.add("第一回　　宴桃園豪傑三結義，斬黃巾英雄首立功");
		end.add("第二回　　張翼德怒鞭督郵，何國舅謀誅宦豎");
		end.add("第三回　　議溫明董卓叱丁原，餽金珠李肅說呂布");
		end.add("第四回　　廢漢帝陳留為皇，謀董賊孟德獻刀");
		end.add("第五回　　發矯詔諸鎮應曹公，破關兵三英戰呂布");

		listDataChild.put(listDataHeader.get(0), first); // 標題, 內容
		listDataChild.put(listDataHeader.get(1), second);
		listDataChild.put(listDataHeader.get(2), end);
	}
}