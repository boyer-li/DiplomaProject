import { clear, getList, save } from '@/api/message/message'

export default {
  name:'msg',
  data() {
    return {
      formVisible: false,
      formTitle: '添加历史消息',
      isAdd: true,
      form: {
        tplCode:'',
        content:'',
        receiver:'',
        type:'',
        id: ''
      },
      listQuery: {
        page: 1,
        limit: 20,
        id: undefined
      },
      rangeDate: undefined,
      total: 0,
      list: null,
      listLoading: true,
      selRow: {}
    }
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      this.fetchData()
    },
    fetchData() {
      this.listLoading = true
      let queryData = this.listQuery
      if(this.rangeDate){
        queryData['startDate'] = this.rangeDate[0]
        queryData['endDate'] = this.rangeDate[1]

      }
      getList(queryData).then(response => {
        this.list = response.data.records
        this.listLoading = false
        this.total = response.data.total
      })
    },
    search() {
      this.listQuery.page = 1
      this.fetchData()
    },
    reset() {
      this.listQuery.startDate = undefined
      this.listQuery.endDate = undefined
      this.rangeDate = ''
      this.listQuery.page = 1
      this.fetchData()
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    fetchNext() {
      this.listQuery.page = this.listQuery.page + 1
      this.fetchData()
    },
    fetchPrev() {
      this.listQuery.page = this.listQuery.page - 1
      this.fetchData()
    },
    fetchPage(page) {
      this.listQuery.page = page
      this.fetchData()
    },
    changeSize(limit) {
      this.listQuery.limit = limit
      this.fetchData()
    },
    clear(){
      this.$confirm('确认清楚所有历史消息?', 'Tips', {
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
         clear().then(response => {
           this.fetchData()
           this.$message({
             type: 'success',
             message: '清楚成功!'
           });
         })
      })
    }

  }
}
