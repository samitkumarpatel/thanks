<template>
  <div class="ui__home">
    <ApplicationError :error="error" :value="errorTxt"/>
    <h2>{{title}}</h2>
    <hr/>
    <section>
      <div class="row">
        <div class="col">
          <strong>Profile</strong>
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
          <strong>Points</strong><hr/>
          <h4>Total Points : <span class="badge badge-success">10</span></h4>
          <br/>
        </div>
        <div class="col">
          <strong>History</strong><hr/>

          <div class="card" v-for="(x,index) in [1,2,3]" :key="index">
            <div class="card-header" id="headingOne">
              <h5 class="mb-0">
                <button class="btn btn-link" data-toggle="collapse" :data-target="'#collapseOne'+index" aria-expanded="true" :aria-controls="'collapseOne'+index">
                  Name : {{index}} has given #5 points
                </button>
              </h5>
            </div>
            <div :id="'collapseOne'+index" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
              <div class="card-body">
                Details to be publish for - {{index}}
              </div>
            </div>
          </div>

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
    
  },
}
</script>
<style scoped>
  div.col{
    text-align:center;
  }
</style>

