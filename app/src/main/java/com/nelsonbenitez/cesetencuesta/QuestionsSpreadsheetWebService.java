package com.nelsonbenitez.cesetencuesta;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface QuestionsSpreadsheetWebService {

    @POST("1FAIpQLScXaO4o2QfNXQuS2uhmwWJuLGX93a3Gc_ihS_wdGni48G-PJA/formResponse")
    @FormUrlEncoded
    Call<Void> completeQuestionnaire(
            @Field("entry.1443336330") String servicio,
            @Field("entry.336413030") String pregunta_uno,
            @Field("entry.1827596187") String pregunta_dos,
            @Field("entry.1759062231") String pregunta_tres,
            @Field("entry.912522231") String pregunta_cuatro,
            @Field("entry.837186199") String pregunta_cinco

    );
}
