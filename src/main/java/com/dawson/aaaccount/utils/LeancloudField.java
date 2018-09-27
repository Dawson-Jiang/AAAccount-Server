package com.dawson.aaaccount.utils;

public class LeancloudField {

	public static class BASE_CLASS {
		public static final String STATE = "state";
	}

	public static class CONSUME_CATEGORY {
		public static final String CLASS_NAME = "ConsumeCategory";
		public static final String NAME = "name";
		public static final String SORT_FLAG = "sortFlag";
	}

	public static class FAMILY {
		public static final String CLASS_NAME = "Family";
		public static final String NUMBER = "ID";// 自定义id
		public static final String CREATOR = "creator";
		public static final String NAME = "name";
		public static final String MEMBER = "members";
		public static final String HEAD = "headPic";
		public static final String MEMBER2 = "members2";
		public static final String TEMP = "isTemp";
		// public static final String CONSUME_TYPE= "consumeTypes";//后期可以考虑自定义类别
	}

	public static class MEMBER {
		public static final String CLASS_NAME = "Member";
		public static final String NAME = "memberName";
	}

	public static class USER {
		public static final String CLASS_NAME = "_User";
		public static final String HEAD = "headPic";
		public static final String NAME = "username";
		public static final String LOGIN_INFO = "loginInfo";
		public static final String AUTH_DATA = "authData";
		// public static final String CONSUME_TYPE = "consumeTypes";//后期可以考虑自定义类别
	}

	public static class DAY_BOOK {
		public static final String CLASS_NAME = "DayBook";
		public static final String CONSUME_CATEGORY = "consumeCategory";
		public static final String FAMILY = "family";
		public static final String RECORDER = "recorder";
		public static final String CONSUMER = "consumers";
		public static final String CONSUMER2 = "consumers2";
		public static final String PAYER = "payer";
		public static final String PAYER2 = "payer2";
		public static final String SETTLE = "settled";
		public static final String MONEY = "money";
		public static final String PICTURES = "pictures";
		public static final String THUM_PICTURES = "thumPictures";
		public static final String DESCRIPTION = "description";
		public static final String DATE = "date";
	}

	public static class SETTLE {
		public static final String CLASS_NAME = "Settle";
		public static final String START_DATE = "startDate";
		public static final String END_DATE = "endDate";
		public static final String CREATOR = "creator";
		public static final String MONEY = "money";
		public static final String FAMILY = "family";
		public static final String SETTLE = "settled";
		public static final String DETAIL = "details";
		public static final String DATE = "date";

	}

	public static class SETTLE_DETAIL {
		public static final String CLASS_NAME = "SettleDetail";
		public static final String USER = "user";
		public static final String PAY = "pay";
		public static final String CONSUME = "consume";
		public static final String SETTLE = "settle";
		public static final String AGREE = "agree";
	}

	public static class AV_FILE {
		public static final String FAMILY_HEAD = "family_head_picture";
		public static final String USER_HEAD = "user_head_picture";
		public static final String DAYBOOK_PIC = "daybook_picture";
		public static final String SYSTEM_LOG = "daybook_picture";
	}

	public static class FEED_BACK {
		public static final String CLASS_NAME = "FeedBack";
		public static final String USER = "creator";
		public static final String TITLE = "title";
		public static final String CONTENT = "content";
		public static final String PHONE = "phone";
	}

	public static class SYSTEM_LOG {
		public static final String CLASS_NAME = "SystemLog";
		public static final String USER = "creator";
		public static final String TITLE = "title";
		public static final String CONTENT = "content";
		public static final String PHONE = "phone";
	}
}
