<template>
  <div class="pomodoro_box">
    <div class="pomodoro_header">
      <span>뽀모도로</span>
      <span @click="$emit('closeBtn')">X</span>
    </div>
    <div class="pomodoro_content">
      <PomodoroSetting v-if="state.pomodoro_setting === true" :studyId="studyId" :pomodoro_setting_data="state.pomodoro_setting_data" @completePomodoroSetting="state.pomodoro_setting = false"/>
      <PomodoroGoalState v-if="state.pomodoro_setting === false" @pomodoroSetting="pomodoroSetting" :key="state.study_pomodoro_goals"/>
      <PomodoroState v-if="state.pomodoro_setting === false" :key="state.study_pomodoro_state && state.study_pomodoro_goals"/>
      <PomodoroTimer v-if="state.pomodoro_setting === false" :studyId="studyId" :key="state.study_pomodoro_goals"/>
    </div>
  </div>
</template>

<script>
import PomodoroSetting from '@/components/Room/Pomodoro/PomodoroSetting'
import PomodoroGoalState from '@/components/Room/Pomodoro/PomodoroGoalState'
import PomodoroState from '@/components/Room/Pomodoro/PomodoroState'
import PomodoroTimer from '@/components/Room/Pomodoro/PomodoroTimer'
import '@/assets/style/Room/pomodoro.scss'
import { computed, onMounted, reactive } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'Pomodoro',
  props: {
    studyId: {
      type: String,
      required: true
    }
  },
  components: {
    PomodoroSetting,
    PomodoroGoalState,
    PomodoroState,
    PomodoroTimer
  },

  setup(props){

    const store = useStore()
    const state = reactive({
      pomodoro_setting: false,
      pomodoro_setting_data: [],

      study_pomodoro_goals: computed(()=> {
        if (store.state.studyPomodoroGoals.length === 0) {
          return null
        }
        else {
          return store.state.studyPomodoroGoals
        }
      }),

      study_pomodoro_state: computed(() => {
        return store.state.studyPomodoroState
      })
    })

    const pomodoroSetting = (value)=>{
      state.pomodoro_setting_data = value
      state.pomodoro_setting = true
    }

    onMounted(() => {
      store.dispatch('getPomodoroGoal', props.studyId)
      store.dispatch('getPomodoroState', props.studyId)
    })

    return {
      state,
      pomodoroSetting
    }
  }
}
</script>

<style>

</style>