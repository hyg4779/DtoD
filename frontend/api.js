export const api = {
  LOG_IN: 'http://localhost:9090/api/authenticate',
  SIGN_UP: 'http://localhost:9090/api/signup',
  USER_INFO_GET: 'http://localhost:9090/api/user/mypage',
  USER_INFO_CHANGE: 'http://localhost:9090/api/user/update',
  OTHER_USER_INFO_GET: 'http://localhost:9090/api/user/',
  NICKNAME_CHECK: 'http://localhost:9090/api/user/checkname/',
  EMAIL_CHECK: 'http://localhost:9090/api/user/checkemail/',


  CREATE_STUDY_ROOM: 'http://localhost:9090/',
  

  GET_FREE_BOARD: 'http://localhost:9090/api/cboard/list',
  GET_FREE_BOARD_DETAIL: 'http://localhost:9090/api/cboard/view/',
  CREATE_FREE_BOARD: 'http://localhost:9090/api/cboard/regist',
  UPDATE_FREE_BOARD: 'http://localhost:9090/api/cboard/update',
  DELETE_FREE_BOARD: 'http://localhost:9090/api/cboard/delete/',

  GET_FREE_BOARD_COMMENT: 'http://localhost:9090/api/ccomment/list/',
  CREATE_FREE_BOARD_COMMENT: 'http://localhost:9090/api/ccomment/regist/',
  UPDATE_FREE_BOARD_COMMENT: 'http://localhost:9090/api/ccomment/update/',
  DELETE_FREE_BOARD_COMMENT: 'http://localhost:9090/api/ccomment/delete/',


  GET_STUDY_BOARD: 'http://localhost:9090/api/sboard/list',
  GET_STUDY_BOARD_DETAIL: 'http://localhost:9090/api/sboard/view/',
  CREATE_STUDY_BOARD: 'http://localhost:9090/api/sboard/regist',
  UPDATE_STUDY_BOARD: 'http://localhost:9090/api/sboard/update/',
  DELETE_STUDY_BOARD: 'http://localhost:9090/api/sboard/delete/',

  GET_STUDY_BOARD_COMMENT: 'http://localhost:9090/api/scomment/list/',
  CREATE_STUDY_BOARD_COMMENT: 'http://localhost:9090/api/scomment/regist/',
  UPDATE_STUDY_BOARD_COMMENT: 'http://localhost:9090/api/scomment/update/',
  DELETE_STUDY_BOARD_COMMENT: 'http://localhost:9090/api/scomment/delete/',
}