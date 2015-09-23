package com.lucifer.razercamera.common;

import java.io.File;

import com.lucifer.razercamera.utils.FileUtils;

import android.os.Environment;



public class APIContext
{
	//Http路径
//	public static final String base_url = "http://115.28.180.167:8081/videomeeting/";
//	public static final String base_url = "http://172.16.1.118:8080/videomeeting/";
//	public static final String base_url = "http://172.16.1.242:8080/videomeeting/";
//	public static final String base_url = "http://172.16.1.154:8080/videomeeting/";//博超
//	public static final String base_url = "http://172.16.1.245/videomeeting/";//维奥
//	public static final String base_url = "http://42.96.251.75:80/videomeeting/";
//	public static final String base_url = "http://115.28.64.49/videomeeting/";
	public static final String base_url = "http://121.42.207.208/videomeeting/";
	
	//推送路径
	public static final String xmpp_host = "115.28.22.208";
	public static final int xmpp_port = 5222;
	public static final String groupChat = "groupchat.cidtech";
	
	//视频路径
	public static final String websocketRUL = "ws://120.24.249.122:8080/ws";
//	public static final String websocketRUL = "ws://58.96.178.228:8080/ws";
	//public static final String websocketRUL = "ws://172.16.1.36:8081/ws";
	
	
	//文件系统及存储路径
	public static String appBashPath;
	
	public static String headImgPath = FileUtils.getSDCardPath() + "userHead/headlogo.jpg";
	
	public static String shareImgPath = Environment.getExternalStorageDirectory().getPath()+
			File.separator + "mpvideo" + File.separator + "screenshot" + File.separator;
	
	public static String shareImgFile = Environment.getExternalStorageDirectory().getPath()+
			File.separator + "mpvideo" + File.separator + "screenshot" + File.separator
			+ "mvshare.png";
	
	
	public static String tokeImg = FileUtils.getSDCardPath() + "mpvideo"+ System.currentTimeMillis() + ".jpg";
	public static String infoBackgroudPath = FileUtils.getSDCardPath() + "backgroud/background.jpg";
	
	public static String unionFile = FileUtils.getSDCardPath() + "userHead/unionfile";

	public static String saveVideoPath = Environment.getExternalStorageDirectory()
			.getPath() + "/DCIM/TempVideo.mp4";
	
	public static String saveSoundPath = Environment.getExternalStorageDirectory()
			.getPath() + "/DCIM/tempSound.amr";
	
	public static String tempSoundPath = Environment.getExternalStorageDirectory()
			.getPath() + "/DCIM/temp";
	
	//Http接口二级路径,OLD
	public static final String userController = "userManager/";
	public static final String MeetingRecordsController = "MeetingRecordsController/";
	
	//Http接口三级路径,OLD 
	public static final String ifLogining = "ifLogining";
	
	//注册后更新Token
	public static final String updateEscpPushInfo = "userRegistController/updateEscpPushInfo";
	
	//更改用户头像
	public static final String updateUserPhotoByMobileAndroid = "updateUserPhotoByMobileAndroid";
	
	//更改密码
	public static final String updateRegistedUserPwd  = "updateRegistedUserPwd";
	
	
	//修改用户名
	public static final String updateRegistedUserNickNameByMobile = "updateRegistedUserNickNameByMobile";
	
	//解除绑定
	public static final String userRegistOut = "userRegistOut";
	
	//是否注册过
	public static final String isband = "isband";
	
	//忘记密码
	public static final String returnPassWord = "returnPassWord";
	
	public static final String friendsController = "FriendsController/";
	
	
	public static final String meetingStartInfo = "meetingStartInfo";
	
	//意见反馈
	public static final String addFeedbackInfo = "addFeedbackInfo";
	
	//聊天记录
	public static final String getMyStartRecords = "getMyStartRecords";
	
	//版本更新
	public static final String returnVerSionInfo = "version/returnVerSionInfo";
	
	//分享
	public static final String appID = "wx54fa8f4632b2001f";
	public static final String appSecret = "7e44ab11bbf723184609ff13113c336d";
//	public static final String shareUrl = "http://115.28.180.167:8080/videomeeting/media/multivideo.html";
	public static final String shareUrl = "http://121.42.207.208/videomeeting/version/downLoad";
//	public static final String shareUrl = "http://172.16.1.151/videomeeting/media/multivideo.html";
	public static final String shareContent = "刷脸的时代都来了，下载多视面对面群聊。下载地址："+ shareUrl;
	
	//新版分享相关
	public static final String shareBaseUrl = base_url+"discuss/shareDiscuss?discussId=";
	public static final String shareDiscussContent = "所有的遇见都逃不过一场久别重逢。";
	
	//邀请
	public static final String inviteContent_front = "在对的时间，遇见对的你。点击下载新版多视，等待只为与你相见。" + shareUrl;
	public static final String inviteContent_middle = "";
	public static final String inviteContent_behind = "";
	
	//摇一摇
	public static final String shake = "videofactionController/videoSharkitoff";
	
	//加好友
	public static final String addFriend = "MongofriendsController/addFriend";
	
	//添加或拒绝好友请求
	public static final String updateFriendRel = "MongofriendsController/updateFriendRel";
	
	//获取好友列表
	public static final String getFriendList = "MongofriendsController/getFriendList";
	
	//删除好友
	public static final String deleteFriend = "MongofriendsController/deleteFriend";
	
	
	//拒绝邀请
	public static final String rejectInvite = "MongofriendsController/rejectInvite";
	
	//当前用户正忙，拒绝对方的群聊邀请
	public static final String rejectInbusy = "MongofriendsController/rejectInbusy";
	
	//goList
	public static final String returnAllFriends = "ContactsfdsController/returnAllFriends";

	//发现
	public static final String imageUrl = "dynamic_folder/";
	
	public static final String updateUserDisInfo = "DiscoverfocusController/updateUserDisInfo";
	public static final String getUserDetailInfoByMobile = "DiscoverfocusController/getUserDetailInfoByMobile";
	public static final String addAndupdateFocusInfo = "DiscoverfocusController/addAndupdateFocusInfo";
	public static final String deleteFocusInfo = "DiscoverfocusController/deleteFocusInfo";
	public static final String getUserDetialInfoByLocation = "DiscoverfocusController/getUserDetailInfoByLocation";
	
	//刷新发现
	public static final String getUserDetailInfoByLocationFlash = "DiscoverfocusController/getUserDetailInfoByLocationFlash";
	
	public static final String getTagsList = "DiscoverfocusController/getTagsList";
	public static final String setTagsList = "DiscoverfocusController/setTagsList";

	//发帖
	public static final String fatie = "ImCircleController/fatie";
	
	//获取最新动态
	public static final String getCircleNew = "ImCircleController/getMyNew";
	
	//刷新动态
	public static final String getMyNewFlash = "ImCircleController/getMyNewFlash";
	
	//获取更多动态
	public static final String getCircleOld = "ImCircleController/getMyOld";
	//获取帖子详细信息
	public static final String getCircleByUuid = "ImCircleController/getCircleByUuid";
	//评论帖子
	public static final String comment = "ImCircleController/comment";
	//对评论进行回复
	public static final String replyComment = "ImCircleController/replyComment";
	//赞帖子
	public static final String praise = "ImCircleController/praise";
	//取消赞
	public static final String praiseCancel = "ImCircleController/praiseCancel";
	//我的关注
	public static final String getSelfFocusInfo = "DiscoverfocusController/getSelfFocusInfo";
	//粉丝
	public static final String getSelfBeFocusInfo = "DiscoverfocusController/getSelfBeFocusInfo";
	//根据图片查看帖子
	public static final String getCircleByPicture = "ImCircleController/getCircleByPicture";
	
	//删除帖子
	public static final String deleteImCircleByUuid = "ImCircleController/deleteImCircleByUuid"; 
	
	//关注简介
	public static final String getFocusUpdateNum = "DiscoverfocusController/getFocusUpdateNum";
	
	//关注详情
	public static final String getFocusUpdateInfo = "DiscoverfocusController/getFocusUpdateInfo";
	
	//活动简介
	public static final String returnActivityEasyInfo = "appointmentMongoController/returnActivityEasyInfo";
	
	//活动详情 
	public static final String returnActivity = "appointmentMongoController/returnActivity";
	
	//参加活动结果
	public static final String getAward = "appointmentMongoController/getAward";
	
	//活动图片
	public static final String activity_file = "activity_file/";
	
	//尖叫
	public static final String sendScreaming = "DiscoverfocusController/sendScreaming";
	
	//聊天时长
	public static final String updateVideoTimeLength = "DiscoverfocusController/updateVideoTimeLength";
	
	//获取是否相互关注
	public static final String getIfEachFocusInfo = "DiscoverfocusController/getIfEachFocusInfo";
	
	//获得女神房间号
	public static final String appointmentMongoController = "appointmentMongoController/groupChat";
	
	//中奖查询
	public static final String checkAwardByMobile = "appointmentMongoController/checkAwardByMobile";
	
	//根据标签查询(加载更多)
	public static final String getFocusInfoByTagId = "DiscoverfocusController/getFocusInfoByTagId";
	
	//根据标签查询
	public static final String getFocusInfoByTagIdFlash = "DiscoverfocusController/getFocusInfoByTagIdFlash";
	
	//邂逅，创建房间
	public static final String createRoom = "appointmentMongoController/createRoom";
	
	//邂逅，加入房间
	public static final String addChat = "appointmentMongoController/addChat";
	
	//邂逅，退出房间
	public static final String destroyRoom = "appointmentMongoController/destroyRoom";
	
	//按昵称查询
	public static final String getFocusInfoByNickname = "DiscoverfocusController/getFocusInfoByNickname";
	
	//设置背景墙
	public static final String setBackImg = "setBackImg";
	
	
	
	
	
	//NEW LuciferTrust
	public static final String userManager = "userManager/";
	//注册
	public static final String userRegist = "userRegist";
	//获取验证码
	public static final String getCode = "getCode";
	//重置密码
	public static final String resetPassword  = "resetPassword";
	//填写用户信息
	public static final String addUserInfo = "addUserInfo";
	//登录
	public static final String userLogin = "userLogin";
	//获取某个用户详细信息
	public static final String findUser = "findUser";
	//显示用户列表（发现）
	public static final String userList = "userList";
	//上传视频
	public static final String updateVideo = "updateVideo";
	
	//标签二级路径
	public static final String lables = "lables/";
	//获取标签
	public static final String findLable = "findLable/";
	//最火用户
	public static final String recommend = "recommend";
	
	public static final String discuss = "discuss/";
	//发帖
	public static final String addDiscuss = "addDiscuss";
	//查看一条帖子
	public static final String findDiscussById = "findDiscussById";
	//查看用户全部发帖
	public static final String findDiscusses = "findDiscusses";
	//记忆（查看收藏的帖子）
	public static final String findMemory = "findMemory";
	//点赞
	public static final String addPraise = "addPraise";
	//删除赞
	public static final String removePraise = "removePraise";
	//增加评论
	public static final String addReply = "addReply";
	//添加收藏
	public static final String addCollection = "addCollection";
	//删除收藏
	public static final String deleteMemory = "deleteMemory";
	//精彩页面
	public static final String findAllDiscusses = "findAllDiscusses";
	//删除帖子
	public static final String deleteDiscussesMe = "deleteDiscussesMe";
	
	
	public static final String discover = "discover/";
	//关注(追随)
	public static final String addAttention = "addAttention";
	//取消关注
	public static final String cancleAttention = "cancleAttention";
	//更改备注名称
	public static final String updateRemarkName = "updateRemarkName";
	//是否相互关注
	public static final String isAttention = "isAttention";
	
	
	public static final String secretWord = "secretWord/";
	//添加密语
	public static final String writeLetter = "writeLetter";
	//插入到我的信箱（拆密语）
	public static final String getLetter = "getLetter";
	//回复密语
	public static final String replyLetter = "replyLetter";
	//查看信箱
	public static final String myLetters = "myLetters";
	//修改私密信息
	public static final String editSecretInfo = "editSecretInfo";
	//查看用户详细信息
	public static final String getPersonInfo = "getPersonInfo";
	
	
	public static final String friends = "friends/";
	//发起视频邀请
	public static final String inviteGroupChat = "inviteGroupChat";
	//新版发起群聊邀请
	public static final String pushInviteGroupChat = "pushInviteGroupChat";
	
	
	//反馈
	public static final String feedBack = "feedBack/";
	public static final String saveFeedBack = "saveFeedBack";
	public static final String addReport = "addReport";
	
	//查看追随
	public static final String findAttention = "findAttention";
	
	//消息列表
	public static final String push = "push/";
	public static final String messageList = "messageList";
	
	//更新token
	public static final String updateToken = "updateToken/";
	
	
	
	
	//Handler
	public static final int NETERROR = 001;
	
	public static final int SPLASH = 002;
	
	public static final int REGISTERSUC = 003;
	public static final int REGISTERFAL = 004;
	public static final int ACCOUNTEXIST = 005;
	
	public static final int GETCODESUC = 006;
	public static final int GETCODEFAL = 007;
	
	public static final int LOGINSUC = 010;
	public static final int LOGINFAL = 011;
	public static final int ACCOUNTNOEXIST = 012;
	public static final int NOINFOMATION = 013;
	
	public static final int LOGODOWNLOADSUC = 014;
	public static final int LOGODOWNLOADFAL = 015;
	
	public static final int SENDSECRETSUC = 016;
	public static final int SENDSECRETFAL = 017;
	
	public static final int GETWHISPERSUC = 020;
	public static final int GETPICSUC = 021;
	public static final int NOPIC = 022;
	public static final int GETLABELSUC = 023;
	
	public static final int GETMEMORYSUC = 024;
	public static final int NOMEMORY = 025;
	public static final int REMOVEMEMORYSUC = 026;
	
	public static final int ZHUISUISUC = 027;
	public static final int YIWANGSUC = 030;
	
	public static final int REMARKSUC = 031;
	
	public static final int WHISPERREPLY = 032;
	public static final int WHISPERMAILSUC = 033;
	public static final int ZHUISUIEACHOTHER = 034;
	public static final int UPDATEVIDEO = 035;
	public static final int GETHUBINFOSUC = 036;
	
	public static final int NAME = 100;
    public static final int SELFDES = 101;
    public static final int SEX = 102;
    public static final int SEXORI = 103;
    public static final int PROFESSION = 104;
    public static final int LIKE = 105;
    public static final int UNLIKE = 106;
    public static final int SKILL = 107;
    public static final int ACTION = 108;
    public static final int ACTIONRESULT = 109;
    public static final int ADDFRIENDSMOBILESUC = 110;
    public static final int ADDFRIENDSMOBILEFAL = 111;
    public static final int PRAISESUC = 112;
    public static final int PRAISEFAL = 113;
    public static final int UNPRAISESUC = 114;
    public static final int UNPRAISEFAL = 115;
    public static final int UNGUANZHUSUC = 116;
    public static final int UPDATEATTENTIONLIST = 117;
    
    public static final int ERRORMOBILE = 118;
    public static final int ERRORPASSWORD = 119;
    public static final int BINDSUC = 120;
    public static final int BINDFAL = 121;
    
    public static final int MODIFYFAL = 122;
    public static final int PERSONUNEXIST = 123;
    public static final int MODIFYSUC = 124;
    
    public static final int OLDPASSWORDERROR = 125;
    public static final int MODIFYPASSWORDSUC = 126;
    public static final int MODIFYPASSWORDFAL = 127;
    
    public static final int NOMORESCREAM = 128;
    public static final int SCREAMSUC = 129;
    
    public static final int UPDATEFANS = 131;
    
    public static final int UPDATEINVITEFRIENDS = 132;
    
    public static final int DELETEFRIENDSSUC = 133;
    public static final int DELETEFRIENDSFAL = 134;
    
    public static final int SAVEPERSONALINFOSUC = 135;
    public static final int SAVEPERSONALINFOFAL = 136;
    
    public static final int GUANZHUSHIPIN = 137;
    
    public static final int GUANZHUSUC = 138;
    public static final int CANCELGUANZHUSUC = 139;
    
    public static final int POSTSUC = 140;
    public static final int POSTFAL = 141;
    
    public static final int SENDMESSAGE = 142;
    
    public static final int FEEDBACKSUC = 143;
    public static final int FEEDBACKFAL = 144;
    
    public static final int SKILLUPDATE = 145;
    
    public static final int POSTLOGOSUC = 146;
    public static final int POSTLOGOFAL = 147;
    
    public static final int RECEIVEFRIEND = 148;
    public static final int REJECTFRIEND = 149;
    
    public static final int SEARCHTAG = 150;
    public static final int SEARCHTAGNOMOREDATA = 151;
    public static final int SEARCHTAGERROR = 152;
    public static final int SaveTempArticle = 153;
    public static final int GetCircleByUrl = 154;
    
    public static final int BrotherFreshSuc = 155;
    public static final int BrotherFreshListEmpty = 156;
    public static final int BrotherFreshFal = 157;
    
    public static final int BrotherNewSuc = 158;
    public static final int BrotherNewListEmpty = 159;
    public static final int BrotherNewFal = 160;
    
    public static final int BrotherMoreSuc = 161;
    public static final int BrotherMoreListEmpty = 162;
    public static final int BrotherMoreFal = 163;
    
    public static final int YanzhengmaSuc = 164;
    public static final int YanzhengmaFal = 165;
    public static final int SetPasswordSuc = 166;
    public static final int SetPasswordFal = 167;
   
    public static final int GetUserInfoSuc = 168;
    public static final int GetUserInfoFal = 169;
    
    public static final int FreshTagSuc = 170;
    
    public static final int wonderfulfreshsuc = 171;
    public static final int wonderfulmoresuc = 172;
    public static final int wonderfulnomore = 173;
    
    public static final int discussInfosuc = 174;
    
    public static final int hotUsersuc = 175;
    public static final int uploadTagSuc = 176;
    public static final int praisesuc = 177;
    public static final int canclepraisesuc = 178;
    
    public static final int getContactListSuc = 179;
    
    public static final int replySuc = 180;
    public static final int replyFal = 181;
    
    public static final int attentionsuc = 182;
    public static final int attentionfal = 183;
    
    public static final int intentList = 184;
    
    public static final int tokenSuc = 185;
    
    public static final int collectDiscussSuc = 186;
    public static final int collectDiscussFal = 187;
    
    public static final int ERRORCODE = 188;
    
    public static final int NOZHUISUIEACHOTHER = 189;
    
    public static final int ERRMOBILE = 190;
    
    public static final int userisband = 191;
    public static final int usernotband = 192;
    
    
    
    
    //视频人数上限
    public static int MAX_PEERCONNECTION_VIDEO = 4;
	//语音人数上限
    public static int MAX_PEERCONNECTION_AUDIO = 8;
    
    
    
    
    //requestCode
    public static int toRemark = 999;
    
    
    
    
    //add by liming 0327
    public static final int GROUPCHAT_NOCONTACT = 200;
    public static final int REGISTER_CONFLICT = 201;
    public static final int CONNECT_ERROR = 202;
    public static final int REGISTOUT = 444;
    public static final int PRISE = 445;
    public static final int UNPRISE = 446;
    
    
    
    
    //SharePreference
    
    //导航页
    public static final String PREFERENCE_NAME_INDEXPAGER = "indexpager";
    public static final String PREFERENCE_KEY_INDEXPAGER = "isAdd";
    
    public static final String PREFERENCE_NAME_TEMPARTICLE = "tempArticle";
    public static final String PREFERENCE_KEY_PATHS = "imagePaths";
    public static final String PREFERENCE_KEY_CONTENT = "content";
    
    //用户信息的preference
    public static final String PREFERENCE_NAME_USER = "user";//存储用户各种信息的文件名
    public static final String PREFERENCE_KEY_MOBILE = "mobile";//用户手机号
    public static final String PREFERENCE_KEY_PASSWORD = "password";//用户密码
    public static final String PREFERENCE_KEY_DEVICEID = "deviceID";//设备号
    public static final String PREFERENCE_KEY_CITYLOCATION = "citylocation";//用户定位的城市
    public static final String PREFERENCE_KEY_MPID = "MPID";//哆号
    public static final String PREFERENCE_KEY_NICKNAME = "NICKNAME";//用户昵称
    public static final String PREFERENCE_KEY_SEX = "sex";//性别
    public static final String PREFERENCE_KEY_QRCODE = "qrcode";//二维码图片
    public static final String PREFERENCE_KEY_HEADPIC = "headPic";//头像 
    public static final String PREFERENCE_KEY_LNG = "lng";//经纬度
    public static final String PREFERENCE_KEY_LAT = "lat";//经纬度
    
    
    //筛选
    public static final String PREFERENCE_NAME_SELECT = "select";//筛选的文件名
    public static final String PREFERENCE_KEY_CITYSLECT = "locaton";//同城或者全部
    public static final String PREFERENCE_KEY_LIVENESS = "liveness";//活跃度
    public static final String PREFERENCE_KEY_SEXSELECT = "sexselect";//同性或者异性
    
    public static final String PREFERENCE_KEY_CITYSRING = "locatonString";//选则的城市的值
    public static final String PREFERENCE_KEY_LIVENESSSTRING = "livenessString";//选择的活跃度的值
    public static final String PREFERENCE_KEY_SEXSELECTSTRING = "sexselectString";//选择的性别的值
    
    public static final String[] IMAGEARRAY= {
    		"http://img5.imgtn.bdimg.com/it/u=2194521584,98042955&fm=21&gp=0.jpg",
    		"http://img5.imgtn.bdimg.com/it/u=2398560752,1454553597&fm=21&gp=0.jpg",
    		"http://img2.imgtn.bdimg.com/it/u=2224311669,2224713447&fm=21&gp=0.jpg",
    		"http://lady.southcn.com/6/images/attachement/jpg/site4/20150528/23/2041655135801317707.jpg",
    		"http://img4q.duitang.com/uploads/blog/201408/14/20140814153449_ycEjr.thumb.700_0.jpeg",
    		"http://cdn.duitang.com/uploads/item/201301/25/20130125121745_nH8Wd.thumb.600_0.jpeg",
    		"http://p3.music.126.net/AWbCgm7vgmpcegoG43E9sw==/5949457418127481.jpg",
    		"http://www.chinadaily.com.cn/language_tips/auvideo/attachement/jpg/site1/20120302/0013729e40c310bad5b326.jpg",
    };
    
    //判断回复窗口的所属的页面，此变量将作为标识
    public static String REPLYWINDOWFROM = "";
    
    public static final String[] shareArray = 
    	{
    		"多人同时在线视频，妈妈再也不心疼我大热天挤地铁去面试了。",
    		"聊天太容易，见一面好难。",
    		"动态视频头像，让你的资料high起来。",
    		"所有的遇见都逃不过一场久别重逢。",
    		"春风十里，不如蜜语。全新定义私密社交，更多信封等你来拆。",
    	};
}
