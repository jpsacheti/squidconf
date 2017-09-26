package br.fema.edu.squidconf.model

data class CacheSize(val diskCacheSize: Int, val memoryCacheSize : Int) : SquidRule {
    override fun getName(): String {
      return "Cache size"
    }

    override fun getRuleString(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}