import com.simplesys.scenario4Prod._

object CommonFilters {
    val raiffAirLines =
        Pos.toUpperCase.like("%JAPAN%AIR%") ||
          Pos.toUpperCase.like("%KOREAN%AIR%") ||
          Pos.toUpperCase.like("%FINNAIR%") ||
          Pos.toUpperCase.like("%Czech%Airlines%".toUpperCase) ||
          Pos.toUpperCase.like("%Qatar%Airways%".toUpperCase) ||
          Pos.toUpperCase.like("%American%Airlines%".toUpperCase) ||
          Pos.toUpperCase.like("%Brussels%Air%".toUpperCase) ||
          Pos.toUpperCase.like("%Lufthansa%".toUpperCase) ||
          Pos.toUpperCase.like("%Austrian%Air%".toUpperCase) ||
          Pos.toUpperCase.like("%Air%Astana%".toUpperCase) ||
          Pos.toUpperCase.like("%Air%India%".toUpperCase) ||
          Pos.toUpperCase.like("%Air%Dolomiti%".toUpperCase) ||
          Pos.toUpperCase.like("%Aegeanair%".toUpperCase) ||
          Pos.toUpperCase.like("%Qantas%Air%".toUpperCase) ||
          Pos.toUpperCase.like("%Qantas%".toUpperCase) ||
          Pos.toUpperCase.like("%Singapore%Air%".toUpperCase) ||
          Pos.toUpperCase.like("%Air%Malta%".toUpperCase) ||
          Pos.toUpperCase.like("%AIR%FRANCE%") ||
          Pos.toUpperCase.like("%KLM%") ||
          Pos.toUpperCase.like("%AVIA%KASSA%") ||
          Pos.toUpperCase.like("%AVIA%CASSA%") ||
          Pos.toUpperCase.like("%AIR%ASIA%") ||
          Pos.toUpperCase.like("%ALITALIA%") ||
          (Pos.toUpperCase.like("%IBERIA%") && (PosLookup.categoryCode.toUpperCase == "transport".toUpperCase || PosLookup.categoryCode.toUpperCase == "travel".toUpperCase)) ||
          Pos.toUpperCase.like("%AIR%BALTIC%") ||
          Pos.toUpperCase.like("%BRITISH%AIRWAYS%") ||
          Pos.toUpperCase.like("%LINGUS%") ||
          Pos.toUpperCase.like("%EASY%JET%") ||
          Pos.toUpperCase.like("%RYAN%AIR%") ||
          Pos.toUpperCase.like("%AIR%CHINA%") ||
          Pos.toUpperCase.like("%cathay%pacific%".toUpperCase) ||
          Pos.toUpperCase.like("%China%Eastern%".toUpperCase) ||
          Pos.toUpperCase.like("%China%Southern%".toUpperCase) ||
          Pos.toUpperCase.like("%Turkish%Airlines%".toUpperCase) ||
          Pos.toUpperCase.like("%AEROFLOT%") ||
          (Pos.toUpperCase.like("%POBEDA%") && (PosLookup.categoryCode.toUpperCase == "transport".toUpperCase || PosLookup.categoryCode.toUpperCase == "travel".toUpperCase)) ||
          Pos.toLowerCase.like("%www.pobeda.aero%") ||
          Pos.toUpperCase.like("%WWW.S7.RU%") ||
          Pos.toUpperCase == "S7" ||
          (Pos.toUpperCase.like("%UTAIR%") && (PosLookup.categoryCode.toUpperCase == "transport".toUpperCase || PosLookup.categoryCode.toUpperCase == "travel".toUpperCase)) ||
          Pos.toUpperCase.like("%URALSKIE%AVIALINII%") ||
          Pos.toUpperCase.like("%URAL%AIRLINES%") ||
          Pos.toUpperCase.like("%ONETWOTRIP%") ||
          Pos.toUpperCase.like("%BILETIX%") ||
          Pos.toUpperCase.like("%KUPIBILET%") ||
          Pos.toUpperCase.like("%BUKBILET%") ||
          Pos.toUpperCase.like("%WIZZ%AIR%") ||
          Pos.toUpperCase.like("%AIR%BERLIN%") ||
          Pos.toUpperCase.like("%GERMAN%WINGS%") ||
          Pos.toUpperCase.like("%EMIRATES%") ||
          Pos.toUpperCase.like("%ETIHAD%") ||
          Pos.toUpperCase.like("%VUELING%")

    val raiffTaxi: BooleanTransactionPart =
        Pos.toUpperCase.like("% UBER %") ||
          Pos.toUpperCase.like("UBER %") ||
          Pos.toUpperCase.like("%TAXI.YANDEX.RU%") ||
          PosLookup.categoryCode.toUpperCase == "transport".toUpperCase &&
            (Pos.toUpperCase.like("%UBER%") ||
              Pos.toUpperCase.like("%YANDEX%TAXI%") ||
              Pos.toUpperCase.like("%TAXI%YANDEX%") ||
              Pos.toUpperCase.like("%GETTAXI%") ||
              Pos.toUpperCase.like("%GETT%") ||
              PosLookup.merchantName.toUpperCase.like("%Ваше Такси%".toUpperCase) ||
              PosLookup.merchantName.toUpperCase.like("%Сити-Мобил%".toUpperCase) ||
              PosLookup.merchantName.toUpperCase.like("%Такси 2412%".toUpperCase) ||
              Pos.toUpperCase.like("%WHEELY%"))

    val raiffOzon =
        Pos.toUpperCase.notLike("%TRAVEL%") &&
          (Pos.toUpperCase.like("%OZON.RU%") ||
            Pos.toUpperCase.like("%INTERNET-MAGAZIN OZON%"))

    val raiffTravel =
        PosLookup.categoryCode.toUpperCase == "travel".toUpperCase ||
          (Pos.toUpperCase.like("%RZD%") && PosLookup.categoryCode.toUpperCase == "transport".toUpperCase) ||
          Pos.toUpperCase.like("%OSTROVOK.RU%") ||
          Pos.toUpperCase.like("%BOOKING.COM%") ||
          Pos.toUpperCase.like("%KUPIBILET%") ||
          Pos.toUpperCase.like("%UFS-ONLINE%") ||
          Pos.toUpperCase.like("%NSK_AEXP3%") ||
          (PosLookup.categoryCode.toUpperCase == "transport".toUpperCase) &&
            (Pos.toUpperCase.like("%AEROEXPRESS%") ||
              PosLookup.merchantName.toUpperCase == "Aeroexpress".toUpperCase ||
              (PosLookup.merchantName.toUpperCase == "Аэроэкспресс".toUpperCase)) ||
          Pos.toUpperCase.like("%ALAMO%RENT%") ||
          Pos.toUpperCase.like("%Buchbinder%".toUpperCase) ||
          Pos.toUpperCase.like("%CARO%RENT%") ||
          Pos.toUpperCase.like("%BUDGET%RENT%") ||
          Pos.toUpperCase.like("%CAR%HIRE%") ||
          Pos.toUpperCase.like("%HIRE%CAR%") ||
          Pos.toUpperCase.like("%CAR%RENT%") ||
          Pos.toUpperCase.like("%RENT%CAR%") ||
          Pos.toUpperCase.like("%KEDDY%") ||
          Pos.toUpperCase.like("%Global%Drive%".toUpperCase) ||
          Pos.toUpperCase.like("%Thrifty%".toUpperCase) ||
          Pos.toUpperCase.like("%CAR%RENTAL%") ||
          Pos.toUpperCase.like("%RENTAL%CAR%") ||
          Pos.toUpperCase.like("%EUROPCAR%") ||
          (Pos.toUpperCase.like("%HERTZ%") && Pos.toUpperCase.notLike("%SHERTZ%")) ||
          Pos.toUpperCase == "AVIS"

}
