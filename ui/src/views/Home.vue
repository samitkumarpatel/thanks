<template>
  <div class="ui__home">
    <ApplicationError :error="error" :value="errorTxt"/>
    <h2>{{title}}</h2>
    <hr/>
    <section>
      <div class="row">
        <div class="col">
          <strong>Your profile</strong>
          <hr/>
          <table class="table table-striped">
            <tbody>
              <tr>
                <td>Name</td>
                <td>NA</td>
              </tr>
              <tr>
                <td>Team</td>
                <td>NA</td>
              </tr>
              <tr>
                <td>Status</td>
                <td>NA</td>
              </tr>
              <tr>
                <td>Other</td>
                <td>NA</td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="col">
          <strong>Your Points</strong><hr/>
        </div>
        <div class="col">
          <strong @click="redirect">History</strong><hr/>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import ApplicationError from '@/components/ApplicationError.vue'

export default {
  name: 'home',
  components: {
    ApplicationError
  },
  data() {
    return {
      title: 'dashboard',
      errorTxt: {
        status : 500,
        message : 'Internal server error'
      },
      error : false
    }
  },methods: {
    redirect() {
      router.go("/team");
    }
  },
}
</script>
<style scoped>
  div.col{
    text-align:center;
  }
</style>

