import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { StudentDashboardComponent } from './Student/student-dashboard/student-dashboard.component';
import { InstructorDashboardComponent } from './Instructor/instructor-dashboard/instructor-dashboard.component';
import { authGuard } from './service/auth.guard';
import { CoursesComponent } from './Student/courses/courses.component';
import { GradesComponent } from './Student/grades/grades.component';
import { ProfileComponent } from './Student/profile/profile.component';
import { ModuleComponent } from './Student/module/module.component';
import { InstructorCoursesComponent } from './Instructor/instructor-courses/instructor-courses.component';
import { InstructorPerformanceComponent } from './Instructor/instructor-performance/instructor-performance.component';
import { InstructorProfileComponent } from './Instructor/instructor-profile/instructor-profile.component';
import { CreateCourseComponent } from './Instructor/create-course/create-course.component';
import { AddModulesComponent } from './Instructor/add-modules/add-modules.component';
import { ViewModulesComponent } from './Instructor/view-modules/view-modules.component';
export const routes: Routes = [
    {
        path:"",
        component:HomeComponent
    },
    {
        path:"login",
        component:LoginComponent
    },
    {
        path:"signup",
        component:SignupComponent
    },
    
    {
        path:"student-dashboard",
        component:StudentDashboardComponent,
        children:[
            {
                path:'profile',
                component:ProfileComponent
            },
            {
                path:'courses',
                component:CoursesComponent
            },
            {
                path:'grades',
                component:GradesComponent
            },
            {
                path:'course/:courseId/modules',
                component:ModuleComponent
            }
        ],
        canActivate: [authGuard],
        data: { role: 'student' },
    },
    {
        path:"instructor-dashboard",
        component:InstructorDashboardComponent,
        children:[
            {
                path:'profile',
                component:InstructorProfileComponent
            },
            {
                path:'courses',
                component:InstructorCoursesComponent,
                
            },
            {
                path:'courses/addCourse',
                pathMatch:'full',
                component:CreateCourseComponent
            },
            {
                path:'courses/:courseId/addModule',
                component:AddModulesComponent
            },
            {
                path:'courses/:courseId/viewModule',
                component:ViewModulesComponent
            },
            {
                path:'performance',
                component:InstructorPerformanceComponent
            }
        ],
        canActivate: [authGuard],
        data: { role: 'instructor' },
    },
    { path: '**', redirectTo: '/' },

];
