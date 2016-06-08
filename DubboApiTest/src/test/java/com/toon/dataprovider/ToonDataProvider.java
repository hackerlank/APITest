package com.toon.dataprovider;

import org.testng.annotations.DataProvider;

public class ToonDataProvider {

	@DataProvider(name="user_getUserId", parallel = true)
	public static Object[][] dpGetUserId() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_getUserId.csv");
	}
	
	@DataProvider(name="user_getUserInfo", parallel = true)
	public static Object[][] dpGetUserInfo() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_getUserInfo.csv");
	}
	
	@DataProvider(name="user_getExtendUserInfo", parallel = true)
	public static Object[][] dpGetExtendUserInfo() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_getExtendUserInfo.csv");
	}
	
	@DataProvider(name="user_queryUserInfo", parallel = true)
	public static Object[][] dpQueryUserInfo() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_queryUserInfo.csv");
	}
	
	@DataProvider(name="user_getUserListByIdList", parallel = true)
	public static Object[][] dpGetUserListByIdList() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_getUserListByIdList.csv");
	}
	
	@DataProvider(name="user_updatePassword", parallel = false)
	public static Object[][] dpGupdatePassword() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_updatePassword.csv");
	}
	
	@DataProvider(name="user_updateUserInfo", parallel = false)
	public static Object[][] dpUpdateUserInfo() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_updateUserInfo.csv");
	}
	
	@DataProvider(name="user_saveUserDeviceInfo", parallel = false)
	public static Object[][] dpSaveUserDeviceInfo() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_saveUserDeviceInfo.csv");
	}
	
	@DataProvider(name="user_getUserDeviceInfoByUserId", parallel = false)
	public static Object[][] dpGetUserDeviceInfoByUserId() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_getUserDeviceInfoByUserId.csv");
	}
	
	@DataProvider(name="user_getDeviceListByUserIdList", parallel = true)
	public static Object[][] dpGetDeviceListByUserIdList() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_getDeviceListByUserIdList.csv");
	}
	
	@DataProvider(name="user_updateUserStatus", parallel = false)
	public static Object[][] dpUpdateUserStatus() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_updateUserStatus.csv");
	}
	
	@DataProvider(name="user_addUserCommonLocation", parallel = false)
	public static Object[][] dpAddUserCommonLocation() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_addUserCommonLocation.csv");
	}
	
	@DataProvider(name="user_updateUserCommonLocation", parallel = false)
	public static Object[][] dpUpdateUserCommonLocation() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_updateUserCommonLocation.csv");
	}
	
	@DataProvider(name="user_deleteUserCommonLocation", parallel = false)
	public static Object[][] dpDeleteUserCommonLocation() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_deleteUserCommonLocation.csv");
	}
	
	@DataProvider(name="user_getAllUserCommonLocationsByUserId", parallel = false)
	public static Object[][] dpGetAllUserCommonLocationsByUserId() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_getAllUserCommonLocationsByUserId.csv");
	}
	
	@DataProvider(name="user_addUserCommonInformation", parallel = true)
	public static Object[][] dpAddUserCommonInformation() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_addUserCommonInformation.csv");
	}
	
	@DataProvider(name="user_updateUserCommonInformation", parallel = true)
	public static Object[][] dpUpdateUserCommonInformation() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_updateUserCommonInformation.csv");
	}
	
	@DataProvider(name="user_deleteUserCommonInformation", parallel = true)
	public static Object[][] dpDeleteUserCommonInformation() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_deleteUserCommonInformation.csv");
	}
	
	@DataProvider(name="user_getAllUserCommonInformationsByUserId", parallel = true)
	public static Object[][] dpGetAllUserCommonInformationsByUserId() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_getAllUserCommonInformationsByUserId.csv");
	}
	
	@DataProvider(name="user_matchFriendsByAddressBook", parallel = true)
	public static Object[][] dpMatchFriendsByAddressBook() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_matchFriendsByAddressBook.csv");
	}
	
	@DataProvider(name="user_updateUserAuthEmail", parallel = false)
	public static Object[][] dpUpdateUserAuthEmail() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_updateUserAuthEmail.csv");
	}
	
	@DataProvider(name="user_updateUserAuthMobilePhone", parallel = false)
	public static Object[][] dpUpdateUserAuthMobilePhone() throws Exception {
		return util.CsvUtil.getCsvData("/data/user_updateUserAuthMobilePhone.csv");
	}
	
	@DataProvider(name="cardFriendRelation_getCardFriendRelationListByCardId", parallel = false)
	public static Object[][] dpGetCardFriendRelationListByCardId() throws Exception {
		return util.CsvUtil.getCsvData("/data/cardFriendRelation_getCardFriendRelationListByCardId.csv");
	}
	
	@DataProvider(name="cardFriendRelation_getSimpleCardFriendRelationListByCardIdList", parallel = false)
	public static Object[][] dpGetSimpleCardFriendRelationListByCardIdList() throws Exception {
		return util.CsvUtil.getCsvData("/data/cardFriendRelation_getSimpleCardFriendRelationListByCardIdList.csv");
	}
	
	@DataProvider(name="cardFriendRelation_getCardFriendRelationListByUserId", parallel = false)
	public static Object[][] dpGetCardFriendRelationListByUserId() throws Exception {
		return util.CsvUtil.getCsvData("/data/cardFriendRelation_getCardFriendRelationListByUserId.csv");
	}
	
	@DataProvider(name="cardFriendRelation_getNewCardFriendRelationListByUserId", parallel = false)
	public static Object[][] dpGetNewCardFriendRelationListByUserId() throws Exception {
		return util.CsvUtil.getCsvData("/data/cardFriendRelation_getNewCardFriendRelationListByUserId.csv");
	}
	
	
	@DataProvider(name="cardFriendRelation_getNewCardFriendRelationListByCardId", parallel = false)
	public static Object[][] dpGetNewCardFriendRelationListByCardId() throws Exception {
		return util.CsvUtil.getCsvData("/data/cardFriendRelation_getNewCardFriendRelationListByCardId.csv");
	}
	
	@DataProvider(name="cardFriendRelation_checkIfFriend", parallel = false)
	public static Object[][] dpCheckIfFriend() throws Exception {
		return util.CsvUtil.getCsvData("/data/cardFriendRelation_checkIfFriend.csv");
	}
	
	@DataProvider(name="cardFriendRelation_queryUserBlack", parallel = false)
	public static Object[][] dpQueryUserBlack() throws Exception {
		return util.CsvUtil.getCsvData("/data/cardFriendRelation_queryUserBlack.csv");
	}

	@DataProvider(name="cardFriendRelation_getFriendCardIdListByCardId", parallel = false)
	public static Object[][] dpGetFriendCardIdListByCardId() throws Exception {
		return util.CsvUtil.getCsvData("/data/cardFriendRelation_getFriendCardIdListByCardId.csv");
	}
	
	@DataProvider(name="cardRecommendRelation_addCardRecommendRelation", parallel = false)
	public static Object[][] dpAddCardRecommendRelation() throws Exception {
		return util.CsvUtil.getCsvData("/data/cardRecommendRelation_addCardRecommendRelation.csv");
	}
	
	@DataProvider(name="cardRecommendRelation_updateCardRecommendRelation", parallel = false)
	public static Object[][] dpUpdateCardRecommendRelation() throws Exception {
		return util.CsvUtil.getCsvData("/data/cardRecommendRelation_updateCardRecommendRelation.csv");
	}
	
	@DataProvider(name="cardRecommendRelation_deleteCardRecommendRelation", parallel = false)
	public static Object[][] dpDeleteCardRecommendRelation() throws Exception {
		return util.CsvUtil.getCsvData("/data/cardRecommendRelation_deleteCardRecommendRelation.csv");
	}
	
	@DataProvider(name="cardRecommendRelation_getCardRecommendRelationListByCardId", parallel = false)
	public static Object[][] dpGetCardRecommendRelationListByCardId() throws Exception {
		return util.CsvUtil.getCsvData("/data/cardRecommendRelation_getCardRecommendRelationListByCardId.csv");
	}
	
	@DataProvider(name="userContacts_addUserContacts", parallel = false)
	public static Object[][] dpAddUserContacts() throws Exception {
		return util.CsvUtil.getCsvData("/data/userContacts_addUserContacts.csv");
	}
	
	@DataProvider(name="userContacts_getUserContactsListByUserId", parallel = false)
	public static Object[][] dpGetUserContactsListByUserId() throws Exception {
		return util.CsvUtil.getCsvData("/data/userContacts_getUserContactsListByUserId.csv");
	}
	
	@DataProvider(name="userContacts_deleteUserContacts", parallel = false)
	public static Object[][] dpDeleteUserContacts() throws Exception {
		return util.CsvUtil.getCsvData("/data/userContacts_deleteUserContacts.csv");
	}
	
	@DataProvider(name="shareFriend_saveShareFriends", parallel = false)
	public static Object[][] dpSaveShareFriends() throws Exception {
		return util.CsvUtil.getCsvData("/data/shareFriend_saveShareFriends.csv");
	}
	
	@DataProvider(name="shareFriend_getShareFriendsByCardId", parallel = false)
	public static Object[][] dpGetShareFriendsByCardId() throws Exception {
		return util.CsvUtil.getCsvData("/data/shareFriend_getShareFriendsByCardId.csv");
	}
	
	@DataProvider(name="shareFriend_getBeShareFriendsByUserId", parallel = false)
	public static Object[][] dpGetBeShareFriendsByUserId() throws Exception {
		return util.CsvUtil.getCsvData("/data/shareFriend_getBeShareFriendsByUserId.csv");
	}
	
	@DataProvider(name="groupRelation_addGroupRelation", parallel = false)
	public static Object[][] dpAddGroupRelation() throws Exception {
		return util.CsvUtil.getCsvData("/data/groupRelation_addGroupRelation.csv");
	}
	
	@DataProvider(name="groupRelation_updateGroupRelation", parallel = false)
	public static Object[][] dpUpdateGroupRelation() throws Exception {
		return util.CsvUtil.getCsvData("/data/groupRelation_updateGroupRelation.csv");
	}
	
	@DataProvider(name="groupRelation_deleteGroupRelation", parallel = false)
	public static Object[][] dpDdeleteGroupRelation() throws Exception {
		return util.CsvUtil.getCsvData("/data/groupRelation_deleteGroupRelation.csv");
	}
	
	@DataProvider(name="groupRelation_batchAddGroupRelation", parallel = false)
	public static Object[][] dpBatchAddGroupRelation() throws Exception {
		return util.CsvUtil.getCsvData("/data/groupRelation_batchAddGroupRelation.csv");
	}
	
	@DataProvider(name="groupRelation_batchDeleteGroupRelation", parallel = false)
	public static Object[][] dpBatchDeleteGroupRelation() throws Exception {
		return util.CsvUtil.getCsvData("/data/groupRelation_batchDeleteGroupRelation.csv");
	}
	
	@DataProvider(name="groupRelation_getGroupRelationListByGroupId", parallel = false)
	public static Object[][] dpGetGroupRelationListByGroupId() throws Exception {
		return util.CsvUtil.getCsvData("/data/groupRelation_getGroupRelationListByGroupId.csv");
	}
	
	@DataProvider(name="groupRelation_getNewGroupRelationListByGroupId", parallel = false)
	public static Object[][] dpGetNewGroupRelationListByGroupId() throws Exception {
		return util.CsvUtil.getCsvData("/data/groupRelation_getNewGroupRelationListByGroupId.csv");
	}
	
	@DataProvider(name="groupRelation_getSimpleGroupRelationListByGroupId", parallel = false)
	public static Object[][] dpGetSimpleGroupRelationListByGroupId() throws Exception {
		return util.CsvUtil.getCsvData("/data/groupRelation_getSimpleGroupRelationListByGroupId.csv");
	}
	
	@DataProvider(name="groupRelation_getCardIdListByGroupId", parallel = false)
	public static Object[][] dpGetCardIdListByGroupId() throws Exception {
		return util.CsvUtil.getCsvData("/data/groupRelation_getCardIdListByGroupId.csv");
	}
	
	@DataProvider(name="groupRelation_getSimpleGroupRelationListByCardId", parallel = false)
	public static Object[][] dpGetSimpleGroupRelationListByCardId() throws Exception {
		return util.CsvUtil.getCsvData("/data/groupRelation_getSimpleGroupRelationListByCardId.csv");
	}
	
	@DataProvider(name="groupRelation_getGroupIdListByCardId", parallel = false)
	public static Object[][] dpGetGroupIdListByCardId() throws Exception {
		return util.CsvUtil.getCsvData("/data/groupRelation_getGroupIdListByCardId.csv");
	}
	
	@DataProvider(name="groupRelation_getGroupRelationByCardIdAndGroupId", parallel = false)
	public static Object[][] dpGetGroupRelationByCardIdAndGroupId() throws Exception {
		return util.CsvUtil.getCsvData("/data/groupRelation_getGroupRelationByCardIdAndGroupId.csv");
	}
	
}
