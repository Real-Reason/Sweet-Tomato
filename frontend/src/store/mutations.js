
export function CHANGE_ISLOGIN (state) {
  const check_jwt = localStorage.getItem('jwt')
  if (check_jwt) {
    state.isLogin = true
  }
  else {
    state.isLogin = false
  }
}

export function GET_STUDY_INFO (state, res) {
  state.studyInfo = res
}

export function GET_STUDY_IMG(state, res) {
  state.studyImg = res
}

export function CREATE_COMMENT (state, res) {
  state.studyComments.push(res)
}

export function GET_COMMENTS (state, res) {
  state.studyComments = res
}

export function GET_DATA (state, res) {
  state.studyData = res
}