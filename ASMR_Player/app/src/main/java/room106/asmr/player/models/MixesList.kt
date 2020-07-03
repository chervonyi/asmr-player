package room106.asmr.player.models

class MixesList {

    private var mList = ArrayList<Mix>()

    fun addMix(mix: Mix) {
        mList.add(mix)
    }

    fun getList(): ArrayList<Mix> {
        return mList
    }

}