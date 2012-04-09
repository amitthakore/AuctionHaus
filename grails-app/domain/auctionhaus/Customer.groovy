package auctionhaus

class Customer {

	transient springSecurityService
  //  static transients = ["springSecurityService"]

	String username
	String password
    Date createdDate
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
    static hasMany = [bids:Bid]

	static constraints = {
        username email:true,blank:false,unique: true
        password blank:false
        createdDate nullable: false,blank:false
        bids nullable: true
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		CustomerRole.findAllByCustomer(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}


	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}


