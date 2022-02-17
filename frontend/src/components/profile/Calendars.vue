<template>
  <v-row class="fill-height">
    <v-col>
      <v-sheet height="auto" width="auto">
        <v-toolbar flat>
          <v-btn outlined class="mr-4" color="grey darken-2" @click="setToday">
            오늘
          </v-btn>
          <v-btn fab text small color="grey darken-2" @click="prev">
            <v-icon small>
              mdi-chevron-left
            </v-icon>
          </v-btn>
          <v-btn fab text small color="grey darken-2" @click="next">
            <v-icon small>
              mdi-chevron-right
            </v-icon>
          </v-btn>
          <v-toolbar-title v-if="$refs.calendar">
            {{ $refs.calendar.title }}
          </v-toolbar-title>
          <v-spacer></v-spacer>
          <!-- <v-menu bottom right>
            <template v-slot:activator="{ on, attrs }" >
              <v-btn outlined color="grey darken-2" v-bind="attrs" v-on="on">
                <span>{{ typeToLabel[type] }}</span>
                <v-icon right>
                  mdi-menu-down
                </v-icon>
              </v-btn>
            </template>
            <v-list>
              <v-list-item @click="type = 'day'">
                <v-list-item-title>일 단위</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = 'week'">
                <v-list-item-title>주 단위</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = 'month'">
                <v-list-item-title>월 단위</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu> -->
        </v-toolbar>
      </v-sheet>
      <v-sheet height="60vh" width="auto">
          <!-- :dark="true" -->
        <v-calendar
          ref="calendar"
          v-model="focus"
          color="primary"
          :events="events"
          :event-color="getEventColor"
          :type="type"
          @change="updateRange"
        ></v-calendar>
          <!-- @click:more="viewDay" -->
          <!-- @click:date="viewDay" -->
          <!-- @click:event="showEvent" -->
        <!-- <v-menu
          v-model="selectedOpen"
          :close-on-content-click="false"
          :activator="selectedElement"
          offset-x
        >
          <v-card color="grey lighten-4" min-width="350px" flat>
            <v-toolbar :color="selectedEvent.color" dark>
              <v-btn icon>
                <v-icon>mdi-pencil</v-icon>
              </v-btn>
              <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
              <v-spacer></v-spacer>
              <v-btn icon>
                <v-icon>mdi-heart</v-icon>
              </v-btn>
              <v-btn icon>
                <v-icon>mdi-dots-vertical</v-icon>
              </v-btn>
            </v-toolbar>
            <v-card-text>
              <span v-html="selectedEvent.details"></span>
            </v-card-text>
            <v-card-actions>
              <v-btn text color="secondary" @click="selectedOpen = false">
                Cancel
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-menu> -->
      </v-sheet>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name:'Calendars',
  props: {
    attend: Array,
    leave: Array
  },
  data: () => ({
    focus: '',
    type: 'month',
    // typeToLabel: {
    //   month: '월 단위',
    //   week: '주 단위',
    //   day: '일 단위',
    // },
    // selectedEvent: {},
    // selectedElement: null,
    // selectedOpen: false,
    events: [],
    colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
    // names: ['Meeting', 'Holiday', 'PTO', 'Travel', 'Event', 'Birthday', 'Conference', 'Party'],

    attendInfo: null,
    leaveInfo: null,
  }),
  // mounted () {
  //   this.$refs.calendar.checkChange()
  // },
  methods: {
    // viewDay ({ date }) {
    //   this.focus = date
    //   this.type = 'day'
    // },
    getEventColor (event) {
      return event.color
    },
    setToday () {
      this.focus = ''
    },
    prev () {
      this.$refs.calendar.prev()
    },
    next () {
      this.$refs.calendar.next()
    },
    // showEvent ({ nativeEvent, event }) {
    //   const open = () => {
    //     this.selectedEvent = event
    //     this.selectedElement = nativeEvent.target
    //     requestAnimationFrame(() => requestAnimationFrame(() => this.selectedOpen = true))
    //   }

    //   if (this.selectedOpen) {
    //     this.selectedOpen = false
    //     requestAnimationFrame(() => requestAnimationFrame(() => open()))
    //   } else {
    //     open()
    //   }

    //   nativeEvent.stopPropagation()
    // },
    updateRange () {
      const events = [];

      for (let i = 0; i < this.attendInfo.length; i++) {

        const name1 = '입실 완료';
        const start_time1 = new Date(this.attendInfo[i]);
        const end_time1 = new Date(this.attendInfo[i]);
        // console.log(start_time1)
        // console.log(end_time1)
        start_time1.setHours(start_time1.getHours()+9)
        end_time1.setHours(end_time1.getHours()+9)

        events.push({
          name: name1,
          start: start_time1,
          end: end_time1,
          color: this.colors[this.rnd(0, this.colors.length - 1)],
          timed: true,
        })
      }

      for (let i = 0; i < this.leaveInfo.length; i++) {

        const name2 = '퇴실 완료 ';
        const start_time2 = new Date(this.leaveInfo[i]);
        const end_time2 = new Date(this.leaveInfo[i]);
        // console.log(start_time2)
        // console.log(end_time2)
        start_time2.setHours(start_time2.getHours()+9)
        end_time2.setHours(end_time2.getHours()+9)

        events.push({
          name: name2,
          start: start_time2,
          end: end_time2,
          color: this.colors[this.rnd(0, this.colors.length - 1)],
          timed: true,
        })
      }

      this.events = events
    },
    rnd (a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a
    },
  },
  created() {
    // console.log(this.attend)
    this.attendInfo = this.attend
    console.log(this.attendInfo)
    // console.log(Date(this.attendInfo) + 9)
    // console.log(this.leave)
    this.leaveInfo = this.leave
    console.log(this.leaveInfo)
  }
}
</script>