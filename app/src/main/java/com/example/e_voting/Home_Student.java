package com.example.e_voting;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_voting.Adapters.NewsMessages;
import com.example.e_voting.CandidateResult.ElectionResults;
import com.example.e_voting.Model.MessageModel;
import com.example.e_voting.Model.Winers;
import com.example.e_voting.NominationPages.Feesh;
import com.example.e_voting.VotingPages.Rayiys_Aitihad_Altalaba;
import com.example.e_voting.VotingPages.ReVote2;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Home_Student extends AppCompatActivity {

    DrawerLayout drawerLayout;
    private DatabaseReference table_user, reference, currentStage, currentMembers, dbEqualsVotes;
    String SSN, name;
    LinearLayout openNominationLinear, voteLinear, electionResultsLinear;
    String formattedDate;
    String countwin, mToken;

    TextView txt_winner1, txt_winner2, tv_currentStage;

    TextView textView1, textView2, textView3;
    ImageView imageView1, imageView2, imageView3;

    private FirebaseDatabase db;
    private RecyclerView recyclerView;
    private ArrayList<MessageModel> list;
    private NewsMessages notificationAdapter;
    private ValueEventListener changeNominationColorListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Calendar myCalendar = Calendar.getInstance();
            int day = myCalendar.get(Calendar.DAY_OF_MONTH);
            int month = myCalendar.get(Calendar.MONTH);
            int year = myCalendar.get(Calendar.YEAR);
            int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = myCalendar.get(Calendar.MINUTE);

            int databaseYear = Integer.parseInt(snapshot.child("nomination").child("date").child("year").getValue().toString());
            int databaseMonth = Integer.parseInt(snapshot.child("nomination").child("date").child("month").getValue().toString());
            int databaseDay = Integer.parseInt(snapshot.child("nomination").child("date").child("day").getValue().toString());
            int databaseHour = Integer.parseInt(snapshot.child("nomination").child("time").child("hour").getValue().toString());
            int databaseMinute = Integer.parseInt(snapshot.child("nomination").child("time").child("minute").getValue().toString());

            if (databaseYear < year) {
                changeColorToGray1();
            } else if (databaseYear > year) {
                changeColorToBlack1();
            } else if (databaseYear == year) {
                if (databaseMonth < month) {
                    changeColorToGray1();
                } else if (databaseMonth > month) {
                    changeColorToBlack1();
                } else if (databaseMonth == month) {
                    if (databaseDay < day) {
                        changeColorToGray1();
                    } else if (databaseDay > day) {
                        changeColorToBlack1();
                    } else if (databaseDay == day) {
                        if (databaseHour < hour) {
                            changeColorToGray1();
                        } else if (databaseHour > hour) {
                            changeColorToBlack1();
                        } else if (databaseHour == hour) {
                            if (databaseMinute < minute) {
                                changeColorToGray1();
                            } else if (databaseMinute > minute) {
                                changeColorToBlack1();
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
    private ValueEventListener changeEqualVoteColorListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Calendar myCalendar = Calendar.getInstance();
            int day = myCalendar.get(Calendar.DAY_OF_MONTH);
            int month = myCalendar.get(Calendar.MONTH);
            int year = myCalendar.get(Calendar.YEAR);
            int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = myCalendar.get(Calendar.MINUTE);

            int databaseYear = Integer.parseInt(snapshot.child("التعادل").child("date").child("year").getValue().toString());
            int databaseMonth = Integer.parseInt(snapshot.child("التعادل").child("date").child("month").getValue().toString());
            int databaseDay = Integer.parseInt(snapshot.child("التعادل").child("date").child("day").getValue().toString());
            int databaseHour = Integer.parseInt(snapshot.child("التعادل").child("time").child("hour").getValue().toString());
            int databaseMinute = Integer.parseInt(snapshot.child("التعادل").child("time").child("minute").getValue().toString());

            if (databaseYear < year) {
                changeColorToGray2();
            } else if (databaseYear > year) {
                changeColorToBlack2();
            } else if (databaseYear == year) {
                if (databaseMonth < month) {
                    changeColorToGray2();
                } else if (databaseMonth > month) {
                    changeColorToBlack2();
                } else if (databaseMonth == month) {
                    if (databaseDay < day) {
                        changeColorToGray2();
                    } else if (databaseDay > day) {
                        changeColorToBlack2();
                    } else if (databaseDay == day) {
                        if (databaseHour < hour) {
                            changeColorToGray2();
                        } else if (databaseHour > hour) {
                            changeColorToBlack2();
                        } else if (databaseHour == hour) {
                            if (databaseMinute < minute) {
                                changeColorToGray2();
                            } else if (databaseMinute > minute) {
                                changeColorToBlack2();
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
    private ValueEventListener changeVoteColorListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Calendar myCalendar = Calendar.getInstance();
            int day = myCalendar.get(Calendar.DAY_OF_MONTH);
            int month = myCalendar.get(Calendar.MONTH);
            int year = myCalendar.get(Calendar.YEAR);
            int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = myCalendar.get(Calendar.MINUTE);

            int databaseYear = Integer.parseInt(snapshot.child("vote").child("date").child("year").getValue().toString());
            int databaseMonth = Integer.parseInt(snapshot.child("vote").child("date").child("month").getValue().toString());
            int databaseDay = Integer.parseInt(snapshot.child("vote").child("date").child("day").getValue().toString());
            int databaseHour = Integer.parseInt(snapshot.child("vote").child("time").child("hour").getValue().toString());
            int databaseMinute = Integer.parseInt(snapshot.child("vote").child("time").child("minute").getValue().toString());

            if (databaseYear < year) {
                changeColorToGray2();
            } else if (databaseYear > year) {
                changeColorToBlack2();
            } else if (databaseYear == year) {
                if (databaseMonth < month) {
                    changeColorToGray2();
                } else if (databaseMonth > month) {
                    changeColorToBlack2();
                } else if (databaseMonth == month) {
                    if (databaseDay < day) {
                        changeColorToGray2();
                    } else if (databaseDay > day) {
                        changeColorToBlack2();
                    } else if (databaseDay == day) {
                        if (databaseHour < hour) {
                            changeColorToGray2();
                        } else if (databaseHour > hour) {
                            changeColorToBlack2();
                        } else if (databaseHour == hour) {
                            if (databaseMinute < minute) {
                                changeColorToGray2();
                            } else if (databaseMinute > minute) {
                                changeColorToBlack2();
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
    private ValueEventListener changeResultsColorListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Calendar myCalendar = Calendar.getInstance();
            int day = myCalendar.get(Calendar.DAY_OF_MONTH);
            int month = myCalendar.get(Calendar.MONTH);
            int year = myCalendar.get(Calendar.YEAR);
            int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = myCalendar.get(Calendar.MINUTE);

            int databaseYear = Integer.parseInt(snapshot.child("vote").child("date").child("year").getValue().toString());
            int databaseMonth = Integer.parseInt(snapshot.child("vote").child("date").child("month").getValue().toString());
            int databaseDay = Integer.parseInt(snapshot.child("vote").child("date").child("day").getValue().toString());
            int databaseHour = Integer.parseInt(snapshot.child("vote").child("time").child("hour").getValue().toString());
            int databaseMinute = Integer.parseInt(snapshot.child("vote").child("time").child("minute").getValue().toString());

            int EdatabaseYear = Integer.parseInt(snapshot.child("التعادل").child("date").child("year").getValue().toString());
            int EdatabaseMonth = Integer.parseInt(snapshot.child("التعادل").child("date").child("month").getValue().toString());
            int EdatabaseDay = Integer.parseInt(snapshot.child("التعادل").child("date").child("day").getValue().toString());
            int EdatabaseHour = Integer.parseInt(snapshot.child("التعادل").child("time").child("hour").getValue().toString());
            int EdatabaseMinute = Integer.parseInt(snapshot.child("التعادل").child("time").child("minute").getValue().toString());

            if (databaseYear < year) {
//                    خلص
                if (EdatabaseYear < year) {
                    changeColorToBlack3();
                } else if (EdatabaseYear > year) {
                    changeColorToGray3();
                } else if (EdatabaseYear == year) {
                    if (EdatabaseMonth < month) {
                        changeColorToBlack3();
                    } else if (EdatabaseMonth > month) {
                        changeColorToGray3();
                    } else if (EdatabaseMonth == month) {
                        if (EdatabaseDay < day) {
                            changeColorToBlack3();
                        } else if (EdatabaseDay > day) {
                            changeColorToGray3();
                        } else if (EdatabaseDay == day) {
                            if (EdatabaseHour < hour) {
                                changeColorToBlack3();
                            } else if (EdatabaseHour > hour) {
                                changeColorToGray3();
                            } else if (EdatabaseHour == hour) {
                                if (EdatabaseMinute < minute) {
                                    changeColorToBlack3();
                                } else if (EdatabaseMinute > minute) {
                                    changeColorToGray3();
                                }
                            }
                        }
                    }
                }
            } else if (databaseYear > year) {
                changeColorToGray3();
            } else if (databaseYear == year) {
                if (databaseMonth < month) {
//                        خلص
                    if (EdatabaseYear < year) {
                        changeColorToBlack3();
                    } else if (EdatabaseYear > year) {
                        changeColorToGray3();
                    } else if (EdatabaseYear == year) {
                        if (EdatabaseMonth < month) {
                            changeColorToBlack3();
                        } else if (EdatabaseMonth > month) {
                            changeColorToGray3();
                        } else if (EdatabaseMonth == month) {
                            if (EdatabaseDay < day) {
                                changeColorToBlack3();
                            } else if (EdatabaseDay > day) {
                                changeColorToGray3();
                            } else if (EdatabaseDay == day) {
                                if (EdatabaseHour < hour) {
                                    changeColorToBlack3();
                                } else if (EdatabaseHour > hour) {
                                    changeColorToGray3();
                                } else if (EdatabaseHour == hour) {
                                    if (EdatabaseMinute < minute) {
                                        changeColorToBlack3();
                                    } else if (EdatabaseMinute > minute) {
                                        changeColorToGray3();
                                    }
                                }
                            }
                        }
                    }
                } else if (databaseMonth > month) {
                    changeColorToGray3();
                } else if (databaseMonth == month) {
                    if (databaseDay < day) {
//                            خلص
                        if (EdatabaseYear < year) {
                            changeColorToBlack3();
                        } else if (EdatabaseYear > year) {
                            changeColorToGray3();
                        } else if (EdatabaseYear == year) {
                            if (EdatabaseMonth < month) {
                                changeColorToBlack3();
                            } else if (EdatabaseMonth > month) {
                                changeColorToGray3();
                            } else if (EdatabaseMonth == month) {
                                if (EdatabaseDay < day) {
                                    changeColorToBlack3();
                                } else if (EdatabaseDay > day) {
                                    changeColorToGray3();
                                } else if (EdatabaseDay == day) {
                                    if (EdatabaseHour < hour) {
                                        changeColorToBlack3();
                                    } else if (EdatabaseHour > hour) {
                                        changeColorToGray3();
                                    } else if (EdatabaseHour == hour) {
                                        if (EdatabaseMinute < minute) {
                                            changeColorToBlack3();
                                        } else if (EdatabaseMinute > minute) {
                                            changeColorToGray3();
                                        }
                                    }
                                }
                            }
                        }
                    } else if (databaseDay > day) {
                        changeColorToGray3();
                    } else if (databaseDay == day) {
                        if (databaseHour < hour) {
//                                خلص
                            if (EdatabaseYear < year) {
                                changeColorToBlack3();
                            } else if (EdatabaseYear > year) {
                                changeColorToGray3();
                            } else if (EdatabaseYear == year) {
                                if (EdatabaseMonth < month) {
                                    changeColorToBlack3();
                                } else if (EdatabaseMonth > month) {
                                    changeColorToGray3();
                                } else if (EdatabaseMonth == month) {
                                    if (EdatabaseDay < day) {
                                        changeColorToBlack3();
                                    } else if (EdatabaseDay > day) {
                                        changeColorToGray3();
                                    } else if (EdatabaseDay == day) {
                                        if (EdatabaseHour < hour) {
                                            changeColorToBlack3();
                                        } else if (EdatabaseHour > hour) {
                                            changeColorToGray3();
                                        } else if (EdatabaseHour == hour) {
                                            if (EdatabaseMinute < minute) {
                                                changeColorToBlack3();
                                            } else if (EdatabaseMinute > minute) {
                                                changeColorToGray3();
                                            }
                                        }
                                    }
                                }
                            }
                        } else if (databaseHour > hour) {
                            changeColorToGray3();
                        } else if (databaseHour == hour) {
                            if (databaseMinute < minute) {
//                                    خلص
                                if (EdatabaseYear < year) {
                                    changeColorToBlack3();
                                } else if (EdatabaseYear > year) {
                                    changeColorToGray3();
                                } else if (EdatabaseYear == year) {
                                    if (EdatabaseMonth < month) {
                                        changeColorToBlack3();
                                    } else if (EdatabaseMonth > month) {
                                        changeColorToGray3();
                                    } else if (EdatabaseMonth == month) {
                                        if (EdatabaseDay < day) {
                                            changeColorToBlack3();
                                        } else if (EdatabaseDay > day) {
                                            changeColorToGray3();
                                        } else if (EdatabaseDay == day) {
                                            if (EdatabaseHour < hour) {
                                                changeColorToBlack3();
                                            } else if (EdatabaseHour > hour) {
                                                changeColorToGray3();
                                            } else if (EdatabaseHour == hour) {
                                                if (EdatabaseMinute < minute) {
                                                    changeColorToBlack3();
                                                } else if (EdatabaseMinute > minute) {
                                                    changeColorToGray3();
                                                }
                                            }
                                        }
                                    }
                                }
                            } else if (databaseMinute > minute) {
                                changeColorToGray3();
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
    private ValueEventListener currentStageListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            Calendar myCalendar = Calendar.getInstance();
            int day = myCalendar.get(Calendar.DAY_OF_MONTH);
            int month = myCalendar.get(Calendar.MONTH);
            int year = myCalendar.get(Calendar.YEAR);
            int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = myCalendar.get(Calendar.MINUTE);

            int databaseYear = Integer.parseInt(snapshot.child("nomination").child("date").child("year").getValue().toString());
            int databaseMonth = Integer.parseInt(snapshot.child("nomination").child("date").child("month").getValue().toString());
            int databaseDay = Integer.parseInt(snapshot.child("nomination").child("date").child("day").getValue().toString());
            int databaseHour = Integer.parseInt(snapshot.child("nomination").child("time").child("hour").getValue().toString());
            int databaseMinute = Integer.parseInt(snapshot.child("nomination").child("time").child("minute").getValue().toString());

            int VoteDatabaseYear = Integer.parseInt(snapshot.child("vote").child("date").child("year").getValue().toString());
            int VoteDatabaseMonth = Integer.parseInt(snapshot.child("vote").child("date").child("month").getValue().toString());
            int VoteDatabaseDay = Integer.parseInt(snapshot.child("vote").child("date").child("day").getValue().toString());
            int VoteDatabaseHour = Integer.parseInt(snapshot.child("vote").child("time").child("hour").getValue().toString());
            int VoteDatabaseMinute = Integer.parseInt(snapshot.child("vote").child("time").child("minute").getValue().toString());

            int EqualVoteDatabaseYear = Integer.parseInt(snapshot.child("التعادل").child("date").child("year").getValue().toString());
            int EqualVoteDatabaseMonth = Integer.parseInt(snapshot.child("التعادل").child("date").child("month").getValue().toString());
            int EqualVoteDatabaseDay = Integer.parseInt(snapshot.child("التعادل").child("date").child("day").getValue().toString());
            int EqualVoteDatabaseHour = Integer.parseInt(snapshot.child("التعادل").child("time").child("hour").getValue().toString());
            int EqualVoteDatabaseMinute = Integer.parseInt(snapshot.child("التعادل").child("time").child("minute").getValue().toString());

            if (databaseYear < year) {

            } else if (databaseYear > year) {
                tv_currentStage.setText("مرحلة الترشح");
            } else if (databaseYear == year) {
                if (databaseMonth < month) {

                } else if (databaseMonth > month) {
                    tv_currentStage.setText("مرحلة الترشح");
                } else if (databaseMonth == month) {
                    if (databaseDay < day) {

                    } else if (databaseDay > day) {
                        tv_currentStage.setText("مرحلة الترشح");
                    } else if (databaseDay == day) {
                        if (databaseHour < hour) {

                        } else if (databaseHour > hour) {
                            tv_currentStage.setText("مرحلة الترشح");
                        } else if (databaseHour == hour) {
                            if (databaseMinute < minute) {

                            } else if (databaseMinute > minute) {
                                tv_currentStage.setText("مرحلة الترشح");
                            }
                        }
                    }
                }
            }

            if (VoteDatabaseYear < year) {

            } else if (VoteDatabaseYear > year) {
                tv_currentStage.setText("مرحلة التصويت");
            } else if (VoteDatabaseYear == year) {
                if (VoteDatabaseMonth < month) {

                } else if (VoteDatabaseMonth > month) {
                    tv_currentStage.setText("مرحلة التصويت");
                } else if (VoteDatabaseMonth == month) {
                    if (VoteDatabaseDay < day) {

                    } else if (VoteDatabaseDay > day) {
                        tv_currentStage.setText("مرحلة التصويت");
                    } else if (VoteDatabaseDay == day) {
                        if (VoteDatabaseHour < hour) {

                        } else if (VoteDatabaseHour > hour) {
                            tv_currentStage.setText("مرحلة التصويت");
                        } else if (VoteDatabaseHour == hour) {
                            if (VoteDatabaseMinute < minute) {

                            } else if (VoteDatabaseMinute > minute) {
                                tv_currentStage.setText("مرحلة التصويت");
                            }
                        }
                    }
                }
            }

            if (EqualVoteDatabaseYear < year) {

            } else if (EqualVoteDatabaseYear > year) {
                tv_currentStage.setText("دورة الاعادة");
            } else if (EqualVoteDatabaseYear == year) {
                if (EqualVoteDatabaseMonth < month) {

                } else if (EqualVoteDatabaseMonth > month) {
                    tv_currentStage.setText("دورة الاعادة");
                } else if (EqualVoteDatabaseMonth == month) {
                    if (EqualVoteDatabaseDay < day) {

                    } else if (EqualVoteDatabaseDay > day) {
                        tv_currentStage.setText("دورة الاعادة");
                    } else if (EqualVoteDatabaseDay == day) {
                        if (EqualVoteDatabaseHour < hour) {

                        } else if (EqualVoteDatabaseHour > hour) {
                            tv_currentStage.setText("دورة الاعادة");
                        } else if (EqualVoteDatabaseHour == hour) {
                            if (EqualVoteDatabaseMinute < minute) {

                            } else if (EqualVoteDatabaseMinute > minute) {
                                tv_currentStage.setText("دورة الاعادة");
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    private ValueEventListener goNominationListener =new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Calendar myCalendar = Calendar.getInstance();
            int day = myCalendar.get(Calendar.DAY_OF_MONTH);
            int month = myCalendar.get(Calendar.MONTH);
            int year = myCalendar.get(Calendar.YEAR);
            int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = myCalendar.get(Calendar.MINUTE);

            int databaseYear = Integer.parseInt(snapshot.child("nomination").child("date").child("year").getValue().toString());
            int databaseMonth = Integer.parseInt(snapshot.child("nomination").child("date").child("month").getValue().toString());
            int databaseDay = Integer.parseInt(snapshot.child("nomination").child("date").child("day").getValue().toString());
            int databaseHour = Integer.parseInt(snapshot.child("nomination").child("time").child("hour").getValue().toString());
            int databaseMinute = Integer.parseInt(snapshot.child("nomination").child("time").child("minute").getValue().toString());

            if (databaseYear < year) {
                openNominationLinear.setEnabled(false);
            } else if (databaseYear > year) {
                checkBeforeGoToNomination();
            } else if (databaseYear == year) {
                if (databaseMonth < month) {
                    openNominationLinear.setEnabled(false);
                } else if (databaseMonth > month) {
                    checkBeforeGoToNomination();
                } else if (databaseMonth == month) {
                    if (databaseDay < day) {
                        openNominationLinear.setEnabled(false);
                    } else if (databaseDay > day) {
                        checkBeforeGoToNomination();
                    } else if (databaseDay == day) {
                        if (databaseHour < hour) {
                            openNominationLinear.setEnabled(false);
                        } else if (databaseHour > hour) {
                            checkBeforeGoToNomination();
                        } else if (databaseHour == hour) {
                            if (databaseMinute < minute) {
                                openNominationLinear.setEnabled(false);
                            } else if (databaseMinute > minute) {
                                checkBeforeGoToNomination();
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
    private ValueEventListener goVotingListener =new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Calendar myCalendar = Calendar.getInstance();
            int day = myCalendar.get(Calendar.DAY_OF_MONTH);
            int month = myCalendar.get(Calendar.MONTH);
            int year = myCalendar.get(Calendar.YEAR);
            int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = myCalendar.get(Calendar.MINUTE);

            int databaseYear = Integer.parseInt(snapshot.child("vote").child("date").child("year").getValue().toString());
            int databaseMonth = Integer.parseInt(snapshot.child("vote").child("date").child("month").getValue().toString());
            int databaseDay = Integer.parseInt(snapshot.child("vote").child("date").child("day").getValue().toString());
            int databaseHour = Integer.parseInt(snapshot.child("vote").child("time").child("hour").getValue().toString());
            int databaseMinute = Integer.parseInt(snapshot.child("vote").child("time").child("minute").getValue().toString());

            if (databaseYear < year) {
                voteLinear.setEnabled(false);
            } else if (databaseYear > year) {
                checkBeforeGoToVote();
            } else if (databaseYear == year) {
                if (databaseMonth < month) {
                    voteLinear.setEnabled(false);
                } else if (databaseMonth > month) {
                    checkBeforeGoToVote();
                } else if (databaseMonth == month) {
                    if (databaseDay < day) {
                        voteLinear.setEnabled(false);
                    } else if (databaseDay > day) {
                        checkBeforeGoToVote();
                    } else if (databaseDay == day) {
                        if (databaseHour < hour) {
                            voteLinear.setEnabled(false);
                        } else if (databaseHour > hour) {
                            checkBeforeGoToVote();
                        } else if (databaseHour == hour) {
                            if (databaseMinute < minute) {
                                voteLinear.setEnabled(false);
                            } else if (databaseMinute > minute) {
                                checkBeforeGoToVote();
                            }
                        }
                    }
                }
            }

            int EdatabaseYear = Integer.parseInt(snapshot.child("التعادل").child("date").child("year").getValue().toString());
            int EdatabaseMonth = Integer.parseInt(snapshot.child("التعادل").child("date").child("month").getValue().toString());
            int EdatabaseDay = Integer.parseInt(snapshot.child("التعادل").child("date").child("day").getValue().toString());
            int EdatabaseHour = Integer.parseInt(snapshot.child("التعادل").child("time").child("hour").getValue().toString());
            int EdatabaseMinute = Integer.parseInt(snapshot.child("التعادل").child("time").child("minute").getValue().toString());

            if (EdatabaseYear < year) {
                voteLinear.setEnabled(false);
            } else if (EdatabaseYear > year) {
                checkBeforeGoToEqualVote();
            } else if (EdatabaseYear == year) {
                if (EdatabaseMonth < month) {
                    voteLinear.setEnabled(false);
                } else if (EdatabaseMonth > month) {
                    checkBeforeGoToEqualVote();
                } else if (EdatabaseMonth == month) {
                    if (EdatabaseDay < day) {
                        voteLinear.setEnabled(false);
                    } else if (EdatabaseDay > day) {
                        checkBeforeGoToEqualVote();
                    } else if (EdatabaseDay == day) {
                        if (EdatabaseHour < hour) {
                            voteLinear.setEnabled(false);
                        } else if (EdatabaseHour > hour) {
                            checkBeforeGoToEqualVote();
                        } else if (EdatabaseHour == hour) {
                            if (EdatabaseMinute < minute) {
                                voteLinear.setEnabled(false);
                            } else if (EdatabaseMinute > minute) {
                                checkBeforeGoToEqualVote();
                            }
                        }
                    }
                }
            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
    private ValueEventListener goResultsListener =new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Calendar myCalendar = Calendar.getInstance();
            int day = myCalendar.get(Calendar.DAY_OF_MONTH);
            int month = myCalendar.get(Calendar.MONTH);
            int year = myCalendar.get(Calendar.YEAR);
            int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = myCalendar.get(Calendar.MINUTE);

            int databaseYear = Integer.parseInt(snapshot.child("vote").child("date").child("year").getValue().toString());
            int databaseMonth = Integer.parseInt(snapshot.child("vote").child("date").child("month").getValue().toString());
            int databaseDay = Integer.parseInt(snapshot.child("vote").child("date").child("day").getValue().toString());
            int databaseHour = Integer.parseInt(snapshot.child("vote").child("time").child("hour").getValue().toString());
            int databaseMinute = Integer.parseInt(snapshot.child("vote").child("time").child("minute").getValue().toString());

            int EdatabaseYear = Integer.parseInt(snapshot.child("التعادل").child("date").child("year").getValue().toString());
            int EdatabaseMonth = Integer.parseInt(snapshot.child("التعادل").child("date").child("month").getValue().toString());
            int EdatabaseDay = Integer.parseInt(snapshot.child("التعادل").child("date").child("day").getValue().toString());
            int EdatabaseHour = Integer.parseInt(snapshot.child("التعادل").child("time").child("hour").getValue().toString());
            int EdatabaseMinute = Integer.parseInt(snapshot.child("التعادل").child("time").child("minute").getValue().toString());

            if (databaseYear < year) {
//                    خلص
                if (EdatabaseYear < year) {
                    checkBeforeGoToResults();
                } else if (EdatabaseYear > year) {
                    electionResultsLinear.setEnabled(false);
                } else if (EdatabaseYear == year) {
                    if (EdatabaseMonth < month) {
                        checkBeforeGoToResults();
                    } else if (EdatabaseMonth > month) {
                        electionResultsLinear.setEnabled(false);
                    } else if (EdatabaseMonth == month) {
                        if (EdatabaseDay < day) {
                            checkBeforeGoToResults();
                        } else if (EdatabaseDay > day) {
                            electionResultsLinear.setEnabled(false);
                        } else if (EdatabaseDay == day) {
                            if (EdatabaseHour < hour) {
                                checkBeforeGoToResults();
                            } else if (EdatabaseHour > hour) {
                                electionResultsLinear.setEnabled(false);
                            } else if (EdatabaseHour == hour) {
                                if (EdatabaseMinute < minute) {
                                    checkBeforeGoToResults();
                                } else if (EdatabaseMinute > minute) {
                                    electionResultsLinear.setEnabled(false);
                                }
                            }
                        }
                    }
                }
            } else if (databaseYear > year) {
                electionResultsLinear.setEnabled(false);
            } else if (databaseYear == year) {
                if (databaseMonth < month) {
//                        خلص
                    if (EdatabaseYear < year) {
                        checkBeforeGoToResults();
                    } else if (EdatabaseYear > year) {
                        electionResultsLinear.setEnabled(false);
                    } else if (EdatabaseYear == year) {
                        if (EdatabaseMonth < month) {
                            checkBeforeGoToResults();
                        } else if (EdatabaseMonth > month) {
                            electionResultsLinear.setEnabled(false);
                        } else if (EdatabaseMonth == month) {
                            if (EdatabaseDay < day) {
                                checkBeforeGoToResults();
                            } else if (EdatabaseDay > day) {
                                electionResultsLinear.setEnabled(false);
                            } else if (EdatabaseDay == day) {
                                if (EdatabaseHour < hour) {
                                    checkBeforeGoToResults();
                                } else if (EdatabaseHour > hour) {
                                    electionResultsLinear.setEnabled(false);
                                } else if (EdatabaseHour == hour) {
                                    if (EdatabaseMinute < minute) {
                                        checkBeforeGoToResults();
                                    } else if (EdatabaseMinute > minute) {
                                        electionResultsLinear.setEnabled(false);
                                    }
                                }
                            }
                        }
                    }
                } else if (databaseMonth > month) {
                    electionResultsLinear.setEnabled(false);
                } else if (databaseMonth == month) {
                    if (databaseDay < day) {
//                            خلص
                        if (EdatabaseYear < year) {
                            checkBeforeGoToResults();
                        } else if (EdatabaseYear > year) {
                            electionResultsLinear.setEnabled(false);
                        } else if (EdatabaseYear == year) {
                            if (EdatabaseMonth < month) {
                                checkBeforeGoToResults();
                            } else if (EdatabaseMonth > month) {
                                electionResultsLinear.setEnabled(false);
                            } else if (EdatabaseMonth == month) {
                                if (EdatabaseDay < day) {
                                    checkBeforeGoToResults();
                                } else if (EdatabaseDay > day) {
                                    electionResultsLinear.setEnabled(false);
                                } else if (EdatabaseDay == day) {
                                    if (EdatabaseHour < hour) {
                                        checkBeforeGoToResults();
                                    } else if (EdatabaseHour > hour) {
                                        electionResultsLinear.setEnabled(false);
                                    } else if (EdatabaseHour == hour) {
                                        if (EdatabaseMinute < minute) {
                                            checkBeforeGoToResults();
                                        } else if (EdatabaseMinute > minute) {
                                            electionResultsLinear.setEnabled(false);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (databaseDay > day) {
                        electionResultsLinear.setEnabled(false);
                    } else if (databaseDay == day) {
                        if (databaseHour < hour) {
//                                خلص
                            if (EdatabaseYear < year) {
                                checkBeforeGoToResults();
                            } else if (EdatabaseYear > year) {
                                electionResultsLinear.setEnabled(false);
                            } else if (EdatabaseYear == year) {
                                if (EdatabaseMonth < month) {
                                    checkBeforeGoToResults();
                                } else if (EdatabaseMonth > month) {
                                    electionResultsLinear.setEnabled(false);
                                } else if (EdatabaseMonth == month) {
                                    if (EdatabaseDay < day) {
                                        checkBeforeGoToResults();
                                    } else if (EdatabaseDay > day) {
                                        electionResultsLinear.setEnabled(false);
                                    } else if (EdatabaseDay == day) {
                                        if (EdatabaseHour < hour) {
                                            checkBeforeGoToResults();
                                        } else if (EdatabaseHour > hour) {
                                            electionResultsLinear.setEnabled(false);
                                        } else if (EdatabaseHour == hour) {
                                            if (EdatabaseMinute < minute) {
                                                checkBeforeGoToResults();
                                            } else if (EdatabaseMinute > minute) {
                                                electionResultsLinear.setEnabled(false);
                                            }
                                        }
                                    }
                                }
                            }
                        } else if (databaseHour > hour) {
                            electionResultsLinear.setEnabled(false);
                        } else if (databaseHour == hour) {
                            if (databaseMinute < minute) {
//                                    خلص
                                if (EdatabaseYear < year) {
                                    checkBeforeGoToResults();
                                } else if (EdatabaseYear > year) {
                                    electionResultsLinear.setEnabled(false);
                                } else if (EdatabaseYear == year) {
                                    if (EdatabaseMonth < month) {
                                        checkBeforeGoToResults();
                                    } else if (EdatabaseMonth > month) {
                                        electionResultsLinear.setEnabled(false);
                                    } else if (EdatabaseMonth == month) {
                                        if (EdatabaseDay < day) {
                                            checkBeforeGoToResults();
                                        } else if (EdatabaseDay > day) {
                                            electionResultsLinear.setEnabled(false);
                                        } else if (EdatabaseDay == day) {
                                            if (EdatabaseHour < hour) {
                                                checkBeforeGoToResults();
                                            } else if (EdatabaseHour > hour) {
                                                electionResultsLinear.setEnabled(false);
                                            } else if (EdatabaseHour == hour) {
                                                if (EdatabaseMinute < minute) {
                                                    checkBeforeGoToResults();
                                                } else if (EdatabaseMinute > minute) {
                                                    electionResultsLinear.setEnabled(false);
                                                }
                                            }
                                        }
                                    }
                                }
                            } else if (databaseMinute > minute) {
                                electionResultsLinear.setEnabled(false);
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
    private ValueEventListener transFromWinnerToCurrent =new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Calendar myCalendar = Calendar.getInstance();
            int day = myCalendar.get(Calendar.DAY_OF_MONTH);
            int month = myCalendar.get(Calendar.MONTH);
            int year = myCalendar.get(Calendar.YEAR);
            int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = myCalendar.get(Calendar.MINUTE);

            int databaseYear = Integer.parseInt(snapshot.child("vote").child("date").child("year").getValue().toString());
            int databaseMonth = Integer.parseInt(snapshot.child("vote").child("date").child("month").getValue().toString());
            int databaseDay = Integer.parseInt(snapshot.child("vote").child("date").child("day").getValue().toString());
            int databaseHour = Integer.parseInt(snapshot.child("vote").child("time").child("hour").getValue().toString());
            int databaseMinute = Integer.parseInt(snapshot.child("vote").child("time").child("minute").getValue().toString());

            if (databaseYear < year) {
                getWinRaeesAt7ad();
                getWinNaeebRaeesAt7ad();
            } else if (databaseYear > year) {
                afterEndVote();
            } else if (databaseYear == year) {
                if (databaseMonth < month) {
                    getWinRaeesAt7ad();
                    getWinNaeebRaeesAt7ad();
                } else if (databaseMonth > month) {
                    afterEndVote();
                } else if (databaseMonth == month) {
                    if (databaseDay < day) {
                        getWinRaeesAt7ad();
                        getWinNaeebRaeesAt7ad();
                    } else if (databaseDay > day) {
                        afterEndVote();
                    } else if (databaseDay == day) {
                        if (databaseHour < hour) {
                            getWinRaeesAt7ad();
                            getWinNaeebRaeesAt7ad();
                        } else if (databaseHour > hour) {
                            afterEndVote();
                        } else if (databaseHour == hour) {
                            if (databaseMinute < minute) {
                                getWinRaeesAt7ad();
                                getWinNaeebRaeesAt7ad();
                            } else if (databaseMinute > minute) {
                                afterEndVote();
                            }
                        }
                    }
                }
            }

            int EdatabaseYear = Integer.parseInt(snapshot.child("التعادل").child("date").child("year").getValue().toString());
            int EdatabaseMonth = Integer.parseInt(snapshot.child("التعادل").child("date").child("month").getValue().toString());
            int EdatabaseDay = Integer.parseInt(snapshot.child("التعادل").child("date").child("day").getValue().toString());
            int EdatabaseHour = Integer.parseInt(snapshot.child("التعادل").child("time").child("hour").getValue().toString());
            int EdatabaseMinute = Integer.parseInt(snapshot.child("التعادل").child("time").child("minute").getValue().toString());

            if (EdatabaseYear < year) {
                ReVote2getWinRaeesAt7ad();
                ReVote2getWinNaeebRaeesAt7ad();
            } else if (EdatabaseYear > year) {
                currentMembers.child("رئيس اتحاد الطلبة").child("name").setValue("في انتظار مرحلة التعادل.. ");
            } else if (EdatabaseYear == year) {
                if (EdatabaseMonth < month) {
                    ReVote2getWinRaeesAt7ad();
                    ReVote2getWinNaeebRaeesAt7ad();
                } else if (EdatabaseMonth > month) {
                    currentMembers.child("رئيس اتحاد الطلبة").child("name").setValue("في انتظار مرحلة التعادل.. ");
                } else if (EdatabaseMonth == month) {
                    if (EdatabaseDay < day) {
                        ReVote2getWinRaeesAt7ad();
                        ReVote2getWinNaeebRaeesAt7ad();
                    } else if (EdatabaseDay > day) {
                        currentMembers.child("رئيس اتحاد الطلبة").child("name").setValue("في انتظار مرحلة التعادل.. ");
                    } else if (EdatabaseDay == day) {
                        if (EdatabaseHour < hour) {
                            ReVote2getWinRaeesAt7ad();
                            ReVote2getWinNaeebRaeesAt7ad();
                        } else if (EdatabaseHour > hour) {
                            currentMembers.child("رئيس اتحاد الطلبة").child("name").setValue("في انتظار مرحلة التعادل.. ");
                        } else if (EdatabaseHour == hour) {
                            if (EdatabaseMinute < minute) {
                                ReVote2getWinRaeesAt7ad();
                                ReVote2getWinNaeebRaeesAt7ad();
                            } else if (EdatabaseMinute > minute) {
                                currentMembers.child("رئيس اتحاد الطلبة").child("name").setValue("في انتظار مرحلة التعادل.. ");
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_student2);

        txt_winner1 = findViewById(R.id.txt_winner1);
        txt_winner2 = findViewById(R.id.txt_winner2);

        drawerLayout = findViewById(R.id.drawer_layout2);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        imageView1 = findViewById(R.id.image1);
        imageView2 = findViewById(R.id.image2);
        imageView3 = findViewById(R.id.image3);

        tv_currentStage = findViewById(R.id.tv_currentStageStudent);

        openNominationLinear = findViewById(R.id.btn_goToNominationStudent);
        voteLinear = findViewById(R.id.btn_goToVote);
        electionResultsLinear = findViewById(R.id.btn_goToCandidateResults);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            SSN = extra.getString("SSN");
            name = extra.getString("name");
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        table_user = database.getReference("Users");
        currentStage = database.getReference("CurrentStage");
        currentMembers = database.getReference("الاعضاء الحاليون");
        dbEqualsVotes = database.getReference("الاشخاص المتعادلين");

        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(SSN).child("nationality").getValue().toString().equals("1") &&
                        snapshot.child(SSN).child("reputation").getValue().toString().equals("1") &&
                        snapshot.child(SSN).child("year").getValue().toString() != "1") {

                    FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(Home_Student.this, instanceIdResult -> {
                        mToken = instanceIdResult.getToken();
                        Log.e("mohamedx", mToken);
                        table_user.child(SSN).child("token").setValue(mToken);
                    });
                } else {
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //الفترة الحالية
        currentStage.addValueEventListener(currentStageListener);

        //تغيير اللوان
        // طلب الترشح
        currentStage.addValueEventListener(changeNominationColorListener);
//        التعادل
        currentStage.addValueEventListener(changeEqualVoteColorListener);
//        التصويت
        currentStage.addValueEventListener(changeVoteColorListener);
//       نتائج الانتخابات
        currentStage.addValueEventListener(changeResultsColorListener);

        transFromWinnerToCurrent();
        showWinnersFromCurrent();

        recyclerView = findViewById(R.id.rv_student);
        db = FirebaseDatabase.getInstance();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Home_Student.this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(Home_Student.this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        getList();

    }

    private void getList() {

        db.getReference("NewsMessages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list = new ArrayList<>();
                notificationAdapter = new NewsMessages(Home_Student.this, list);
                recyclerView.setAdapter(notificationAdapter);
                for (DataSnapshot child : snapshot.getChildren()) {
                    MessageModel contractorModel = child.getValue(MessageModel.class);
                    list.add(contractorModel);
                    notificationAdapter.notifyItemInserted(list.size() - 1);
                    notificationAdapter.getItemCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Home_Student.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void transFromWinnerToCurrent() { currentStage.addValueEventListener(transFromWinnerToCurrent);}

    // بنجيب الاشخاص الذين يملكون اعلي نسبة تصويت
    private void getWinRaeesAt7ad() {

        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("رئيس اتحاد الطلبة");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                        countwin = String.valueOf(snapshot.child("count").getValue());
                        sameNumberOfVoteRaeesAt7ad(countwin);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    // لو شخص واحد بنعلنه كفائز لو اكثر بنسجلهم في الداتا علشان يتم عمل انتخابات جديدة ليهم
    private void sameNumberOfVoteRaeesAt7ad(String countwin) {
        final List<Winers> winnerList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("رئيس اتحاد الطلبة");
        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                        winnerList.add(new Winers(namewin, countwin, idtwin));
                    }
                }
                if (winnerList.size() > 1) {
                    //handle here to start process again
                    for (Winers item : winnerList) {
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        dbEqualsVotes.child("رئيس اتحاد الطلبة").child(SSN).setValue(item);
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("رئيس اتحاد الطلبة").child("name");
                        reference.setValue("في انتظار التصويت..");
                        currentMembers.child("رئيس اتحاد الطلبة").child("name").setValue("في انتظار التصويت..");

                    }

                } else {
                    //only one winner
                    for (Winers item : winnerList) {
                        item.getName();
                        item.getCount();
                        item.getId();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("رئيس اتحاد الطلبة");
                        reference.setValue(item);
                        currentMembers.child("رئيس اتحاد الطلبة").setValue(item);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getWinNaeebRaeesAt7ad() {
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("نائب رئيس اتحاد الطلبة");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                        countwin = String.valueOf(snapshot.child("count").getValue());
                        sameNumberOfVoteNaeebRaeesAt7ad(countwin);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void sameNumberOfVoteNaeebRaeesAt7ad(String countwin) {
        final List<Winers> winnerList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("نائب رئيس اتحاد الطلبة");
        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                        winnerList.add(new Winers(namewin, countwin, idtwin));
                    }
                }
                if (winnerList.size() > 1) {
                    //handle here to start process again
                    for (Winers item : winnerList) {
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        dbEqualsVotes.child("نائب رئيس اتحاد الطلبة").child(SSN).setValue(item);
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("نائب رئيس اتحاد الطلبة").child("name");
                        reference.setValue("في انتظار التصويت..");
                        currentMembers.child("نائب رئيس اتحاد الطلبة").child("name").setValue("في انتظار التصويت..");


                    }

                } else {
                    //only one winner
                    for (Winers item : winnerList) {
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("نائب رئيس اتحاد الطلبة");
                        reference.setValue(item);
                        currentMembers.child("نائب رئيس اتحاد الطلبة").setValue(item);


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showWinnersFromCurrent() {
        currentMembers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String name = snapshot.child("رئيس اتحاد الطلبة").child("name").getValue().toString();
                txt_winner1.setText(name);
                String name2 = snapshot.child("نائب رئيس اتحاد الطلبة").child("name").getValue().toString();
                txt_winner2.setText(name2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    // التعادل
    private void ReVote2getWinRaeesAt7ad() {
        reference = FirebaseDatabase.getInstance()
                .getReference().child("الاشخاص المتعادلين").child("رئيس اتحاد الطلبة");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                        countwin = String.valueOf(snapshot.child("count").getValue());

                        final List<Winers> winnerList = new ArrayList<>();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الاشخاص المتعادلين").child("رئيس اتحاد الطلبة");
                        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                                        winnerList.add(new Winers(namewin, countwin, idtwin));
                                    }
                                }
                                if (winnerList.size() > 1) {
                                    currentMembers.child("رئيس اتحاد الطلبة").child("name").setValue("في حالة تعادل");

                                } else {
                                    //only one winner
                                    for (Winers item : winnerList) {
                                        item.getName();
                                        item.getCount();
                                        item.getId();
                                        String SSN = item.getId();
                                        reference = FirebaseDatabase.getInstance()
                                                .getReference().child("الفائزون").child("رئيس اتحاد الطلبة");
                                        reference.setValue(item);
                                        currentMembers.child("رئيس اتحاد الطلبة").setValue(item);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ReVote2getWinNaeebRaeesAt7ad() {
        reference = FirebaseDatabase.getInstance()
                .getReference().child("الاشخاص المتعادلين").child("نائب رئيس اتحاد الطلبة");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    if (datasnapshot.exists()) {
                        for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                            String countwin = String.valueOf(snapshot.child("count").getValue().toString());

                            final List<Winers> winnerList = new ArrayList<>();
                            reference = FirebaseDatabase.getInstance()
                                    .getReference().child("الاشخاص المتعادلين").child("نائب رئيس اتحاد الطلبة");
                            Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                            String namewin = String.valueOf(snapshot1.child("name").getValue());
                                            Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                                            String idtwin = String.valueOf(snapshot1.child("id").getValue());
                                            winnerList.add(new Winers(namewin, countwin, idtwin));
                                        }
                                    }
                                    if (winnerList.size() > 1) {
                                        currentMembers.child("نائب رئيس اتحاد الطلبة").child("name").setValue("في حالة تعادل");
                                    } else {
                                        //only one winner
                                        for (Winers item : winnerList) {
                                            item.getName();
                                            item.getCount();
                                            item.getId();
                                            String SSN = item.getId();
                                            reference = FirebaseDatabase.getInstance()
                                                    .getReference().child("الفائزون").child("نائب رئيس اتحاد الطلبة");
                                            currentMembers.child("نائب رئيس اتحاد الطلبة").setValue(item);
                                            reference.setValue(item);
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    //toDo
    //الترشح
    public void openNomination(View view) { currentStage.addValueEventListener(goNominationListener); }
    // التصويت
    public void openVote(View view) {
        currentStage.addValueEventListener(goVotingListener);
    }
    //نتائح الانتخابات
    public void ElectionResults(View view) { currentStage.addValueEventListener(goResultsListener); }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickMenu(View view) { openDrawer(drawerLayout); }

    public static void closeDrawer(DrawerLayout dr) {
        if (dr.isDrawerOpen((GravityCompat.START))) {
            dr.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    @Override
    public void onBackPressed() {
        Intent intent2 = new Intent(this, SignIn.class);
        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent2);
        finish();
    }

    private void checkBeforeGoToNomination() {
        table_user.child(SSN).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("userConditionNomination").getValue().toString().equals("1") &&
                        snapshot.child("nationality").getValue().toString().equals("1") &&
                        snapshot.child("reputation").getValue().toString().equals("1") &&
                        snapshot.child("year").getValue().toString() != "1") {

                    Intent intent = new Intent(Home_Student.this, Feesh.class);
                    intent.putExtra("SSN", SSN);
                    intent.putExtra("name", name);
                    startActivity(intent);
                    finish();


                } else {
                    if (!isFinishing()) {

                        new AlertDialog.Builder(Home_Student.this)
                                .setTitle("عذرا")
                                .setMessage("لقد قمت بالترشح او لا تستوفي شروطك الترشح")
                                .setPositiveButton("حسنا", null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void checkBeforeGoToVote() {
        table_user.child(SSN).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("userConditionVote").getValue().toString().equals("1") &&
                        snapshot.child("nationality").getValue().toString().equals("1") &&
                        snapshot.child("reputation").getValue().toString().equals("1") &&
                        snapshot.child("year").getValue().toString() != "1") {

                    Intent intent = new Intent(Home_Student.this, Rayiys_Aitihad_Altalaba.class);
                    intent.putExtra("SSN", SSN);
                    intent.putExtra("name", name);
                    startActivity(intent);
                    finish();


                } else {
                    if (!isFinishing()) {

                        new AlertDialog.Builder(Home_Student.this)
                                .setTitle("عذرا")
                                .setMessage("لقد قمت بالترشح او لا تستوفي شروطك الترشح")
                                .setPositiveButton("حسنا", null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void checkBeforeGoToEqualVote() {
        table_user.child(SSN).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("userConditionVote").getValue().toString().equals("1") &&
                        snapshot.child("nationality").getValue().toString().equals("1") &&
                        snapshot.child("reputation").getValue().toString().equals("1") &&
                        snapshot.child("year").getValue().toString() != "1") {

                    Intent intent = new Intent(Home_Student.this, ReVote2.class);
                    intent.putExtra("SSN", SSN);
                    intent.putExtra("name", name);
                    startActivity(intent);
                    finish();


                } else {
                    if (!isFinishing()) {

                        new AlertDialog.Builder(Home_Student.this)
                                .setTitle("عذرا")
                                .setMessage("لقد قمت بالترشح او لا تستوفي شروطك الترشح")
                                .setPositiveButton("حسنا", null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void checkBeforeGoToResults() {
        Intent intent = new Intent(Home_Student.this, ElectionResults.class);
        intent.putExtra("SSN", SSN);
        intent.putExtra("name", name);
        startActivity(intent);
        finish();
    }

    private void changeColorToBlack1() {
        textView1.setTextColor(Color.BLACK);
        imageView1.setColorFilter(getResources().getColor(R.color.black));
    }

    private void changeColorToBlack2() {
        textView2.setTextColor(Color.BLACK);
        imageView2.setColorFilter(getResources().getColor(R.color.black));
    }

    private void changeColorToBlack3() {
        textView3.setTextColor(Color.BLACK);
        imageView3.setColorFilter(getResources().getColor(R.color.black));
    }

    private void changeColorToGray1() {
        textView1.setTextColor(Color.GRAY);
        imageView1.setColorFilter(getResources().getColor(R.color.gray));
    }

    private void changeColorToGray2() {
        textView2.setTextColor(Color.GRAY);
        imageView2.setColorFilter(getResources().getColor(R.color.gray));
    }

    private void changeColorToGray3() {
        textView3.setTextColor(Color.GRAY);
        imageView3.setColorFilter(getResources().getColor(R.color.gray));
    }

    private void afterEndVote() {
        reference = FirebaseDatabase.getInstance()
                .getReference().child("الفائزون");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name1 = snapshot.child("رئيس اتحاد الطلبة").child("name").getValue().toString();
                String name2 = snapshot.child("نائب رئيس اتحاد الطلبة").child("name").getValue().toString();
                currentMembers.child("رئيس اتحاد الطلبة").child("name").setValue(name1);
                currentMembers.child("نائب رئيس اتحاد الطلبة").child("name").setValue(name2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void btnCurrentMembers(View view) {
        Intent intent = new Intent(Home_Student.this, CurrentMember.class);
        startActivity(intent);
    }

    public void ClickLogOut(View view) {
        //الفترة الحالية
        currentStage.removeEventListener(currentStageListener);

        //تغيير اللوان
        // طلب الترشح
        currentStage.removeEventListener(changeNominationColorListener);
//        التعادل
        currentStage.removeEventListener(changeEqualVoteColorListener);
//        التصويت
        currentStage.removeEventListener(changeVoteColorListener);
//       نتائج الانتخابات
        currentStage.removeEventListener(changeResultsColorListener);

        currentStage.removeEventListener(goNominationListener);
        currentStage.removeEventListener(goVotingListener);
        currentStage.removeEventListener(goResultsListener);

        currentStage.removeEventListener(transFromWinnerToCurrent);

        Intent intent2 = new Intent(Home_Student.this, SignIn.class);
        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent2);
        finish();
    }
}