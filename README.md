# Responsi 1 Mobile H1D023069-SS Lazio

<b> Nama: </b> Khonsaa Hilmi Mufiida <br>
<b> NIM: </b> H1D023069 <br>
<b> Shift Baru: </b> D <br>
<b> Shift Asal: </b> A <br>
<b> Klub: </b> SS Lazio (ID Club: 110) <br>

# Video Demo Aplikasi


# Alur Data
<b> 1. Inisialisasi API Service <br><br> </b>
FootballApiService.create()
├── Membuat OkHttpClient dengan interceptor
│   ├── AuthInterceptor: Menambahkan X-Auth-Token header
│   └── LoggingInterceptor: Logging request/response
├── Konfigurasi Retrofit
│   ├── Base URL: https://api.football-data.org/v4/
│   └── GsonConverterFactory untuk parsing JSON
└── Return FootballApiService instance <br><br>

<b> 2. Repository Layer <br><br> </b>
TeamRepository.getInstance()
└── getTeamData(teamId)
    ├── Memanggil apiService.getTeamById(teamId)
    ├── Menangani response dengan try-catch
    └── Return Result<TeamResponse>
        ├── Success: Data team lengkap
        └── Failure: Exception error <br><br>
   
<b> 3. ViewModel Layer <br><br> </b>
TeamViewModel.loadTeamData(teamId)
├── Set _isLoading = true
├── Launch coroutine di viewModelScope
├── Memanggil repository.getTeamData(teamId)
├── Set _isLoading = false
└── Update LiveData berdasarkan result
    ├── onSuccess: Update _teamData
    └── onFailure: Update _error <br><br>
   
<b> 4. UI Layer (MainActivity) </b><br><br>
MainActivity.onCreate()
├── Initialize binding dan ViewModel
├── Setup observers untuk LiveData
│   ├── teamData observer
│   │   ├── Load logo klub dengan Glide
│   │   ├── Set nama klub
│   │   └── Set informasi klub
│   ├── isLoading observer
│   │   ├── Show/hide ProgressBar
│   │   └── Show/hide ContentLayout
│   └── error observer
│       └── Show Toast error message
├── Setup click listeners untuk navigasi
│   ├── Club History → ClubHistoryActivity
│   ├── Head Coach → HeadCoachActivity (passing Coach data)
│   └── Team Squad → TeamSquadActivity (passing Squad list)
└── Trigger viewModel.loadTeamData(TEAM_ID) <br><br>
   
5. Detail Flow untuk Setiap Komponen <br><br>
    <b> - API Request Flow </b> <br>
        MainActivity.loadTeamData(110)
            ↓
        TeamViewModel.loadTeamData(110)
            ↓
        TeamRepository.getTeamData(110)
            ↓
        FootballApiService.getTeamById(110)
            ↓
        OkHttpClient mengirim GET request
            ↓
        URL: https://api.football-data.org/v4/teams/110
        Headers: X-Auth-Token: [API_TOKEN]
            ↓
        Server Response (JSON)
            ↓
        GsonConverterFactory parse JSON → TeamResponse object
            ↓
        Repository return Result.success(TeamResponse)
            ↓
        ViewModel update _teamData LiveData
            ↓
        MainActivity observer menerima data
            ↓
        UI diupdate dengan data dari API <br><br>
      
    <b> - Navigation Flow </b> <br>
       MainActivity (Data sudah loaded)
            ↓
        User clicks Card
            ↓
        Click Listener executed
            ↓
        Create Intent dengan data yang diperlukan
            │
            ├── Club History
            │   └── Intent + TeamResponse (Parcelable)
            │
            ├── Head Coach
            │   └── Intent + Coach data (Parcelable)
            │
            └── Team Squad
                └── Intent + ArrayList<Player> (Parcelable)
            ↓
        startActivity(intent)
            ↓
        Target Activity receives data via intent extras
            ↓
        Display data in UI <br><br>
      
    <b> - Player List Flow (TeamSquadActivity) </b> <br>
        TeamSquadActivity.onCreate()
              ↓
          Receive ArrayList<Player> dari intent
              ↓
          Sort players by position
              ├── GOALKEEPER → 1
              ├── DEFENCE → 2
              ├── MIDFIELD → 3
              ├── OFFENCE → 4
              └── Others → 5
              ↓
          Create PlayerAdapter(sortedSquad)
              ↓
          Set adapter to RecyclerView
              ↓
          RecyclerView renders player items
              └── Each item colored by PositionColor.getColor() <br><br>
      
    <b> - Player Detail Flow </b> <br>
          User clicks Player Card
              ↓
          PlayerAdapter.ViewHolder click listener
              ↓
          Create PlayerDetailBottomSheet.newInstance(player)
              ├── Convert Player to JSON string
              └── Pass as Bundle argument
              ↓
          Show BottomSheet via supportFragmentManager
              ↓
          PlayerDetailBottomSheet.onCreateView()
              ├── Parse JSON back to Player object
              └── Display player details:
                  ├── Name
                  ├── Date of Birth
                  ├── Nationality
                  └── Position <br><br>

# Screenshot Aplikasi
