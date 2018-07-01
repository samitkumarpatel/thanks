<template>
    <div id="member__profile">
        <table class="table table-striped" :id="profile.id">
            <tbody>
                <tr>
                    <td>Employee Id</td>
                    <td class="result">{{profile.empid}}</td>
                </tr>
                <tr>
                    <td>First Name</td>
                    <td class="result">{{profile.firstname}}</td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td class="result">{{profile.lastname}}</td>
                </tr>
                <tr>
                    <td>Role</td>
                    <td class="result">{{profile.role}}</td>
                </tr>
                <tr>
                    <td>Part Of Team</td>
                    <td class="result">{{profile.teamId}}</td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td class="result">{{profile.status ? 'ACTIVE' : 'IN-ACTIVE'}}</td>
                </tr>
                <tr>
                    <td>Technology</td>
                    <td class="result">
                    <span class="badge badge-secondary" v-for="(tech,index) of profile.technology" :key="index">{{tech}}</span>
                    </td>
                </tr>
                <tr>
                    <td>Address Details</td>
                    <td class="result">
                    <div v-if="profile.address==null">-</div>
                    <div v-else>
                        <p>country : {{profile.address.country}}</p> 
                        <p>city : {{profile.address.city}}</p>
                        <p>pin : {{profile.address.pin}}</p>
                    </div>
                    </td>
                </tr>
                <tr>
                    <td>Contact Details</td>
                    <td class="result">
                    <div v-if="profile.contact==null">-</div>
                    <div v-else>
                        <p>email : {{profile.contact.email}}</p> 
                        <p>mobile : {{profile.contact.mobile}}</p>
                        <p>llt : {{profile.contact.llt}}</p>
                    </div>
                    </td>
                </tr>
                <tr>
                    <td>Profile Created On</td>
                    <td class="result">{{profile.createdDate}}</td>
                </tr>
                <tr>
                    <td>Last Update Profile</td>
                    <td class="result">{{profile.updateDate}}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
export default {
  name: "profile",
  props: {
    profile: {
      type: Object,
      default: {}
    }
  }
};
</script>

<style scoped>
    #member__profile{
        margin-top: 20px;
    }
    .result {
        text-align: left;
    }
    .result span {
        margin-left: 2px;
    }
</style>